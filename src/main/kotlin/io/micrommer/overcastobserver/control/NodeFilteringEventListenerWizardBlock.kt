package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.NodeFilteringEventListenerContext
import io.micrommer.overcastobserver.exception.MalformedInstrumentException
import io.micrommer.overcastobserver.exception.TriggerException

class NodeFilteringEventListenerWizardBlock(private val nodeFilteringEventListenerContext: NodeFilteringEventListenerContext) :
    NodeFilteringEventListenerWizard, Action(nodeFilteringEventListenerContext) {
    override fun runListener(nodeFilteringEventListenerContext: NodeFilteringEventListenerContext) {
        EventWatcher(onEventReceived = { action, event ->
            val conditions = listOf(
                // based on https://github.com/kubernetes/kubernetes/blob/master/pkg/kubelet/events/event.go
                event.reason.equals("Scheduled", true),
                event.fullResourceName == nodeFilteringEventListenerContext.nodeName
            )
            // todo : add available conditions matcher DSL
            if (conditions.all { it }) {
                throw TriggerException()
            }
        }, onClosed = { cause ->
            logException(cause)
            throw MalformedInstrumentException(cause)
        }) registerOn cxt.client
    }

    override fun run() {
        runListener(nodeFilteringEventListenerContext)
    }
}
