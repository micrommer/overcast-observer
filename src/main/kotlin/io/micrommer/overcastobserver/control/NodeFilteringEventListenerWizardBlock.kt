package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.api.model.events.v1.Event
import io.fabric8.kubernetes.client.Watcher
import io.fabric8.kubernetes.client.WatcherException
import io.micrommer.overcastobserver.domain.NodeFilteringEventListenerContext
import io.micrommer.overcastobserver.exception.MalformedInstrumentException
import io.micrommer.overcastobserver.exception.TriggerException

class NodeFilteringEventListenerWizardBlock(private val nodeFilteringEventListenerContext: NodeFilteringEventListenerContext) :
    NodeFilteringEventListenerWizard, Action(nodeFilteringEventListenerContext) {
    override fun runListener(nodeFilteringEventListenerContext: NodeFilteringEventListenerContext) {
        val watcher: Watcher<Event> = object : Watcher<Event> {
            override fun eventReceived(action: Watcher.Action, event: Event) {
                val conditions = listOf(
                    event.reason.equals("Scheduled", true),
                    event.fullResourceName == nodeFilteringEventListenerContext.nodeName
                )
                // todo : add available conditions matcher DSL
                if (conditions.all { it }) {
                    throw TriggerException()
                }

            }

            override fun onClose(cause: WatcherException) {
                logException(cause)
                throw MalformedInstrumentException(cause)
            }
        }
        cxt.client.events().v1().events().watch(watcher)
    }

    override fun run() {
        runListener(nodeFilteringEventListenerContext)
    }
}
