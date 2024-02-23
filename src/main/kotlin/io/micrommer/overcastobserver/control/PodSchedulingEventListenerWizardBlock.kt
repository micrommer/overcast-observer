package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodSchedulingEventListenerContext
import io.micrommer.overcastobserver.exception.TriggerException

class PodSchedulingEventListenerWizardBlock(private val podSchedulingEventListenerContext: PodSchedulingEventListenerContext) :
    PodSchedulingEventListenerWizard, Action(podSchedulingEventListenerContext) {
    override fun runListener(podSchedulingEventListenerContext: PodSchedulingEventListenerContext) {
        EventWatcher(onEventReceived = { action, event ->
            val conditions = listOf(
                // based on https://github.com/kubernetes/kubernetes/blob/master/pkg/kubelet/events/event.go
                event.reason.equals("Started", true),
                event.fullResourceName == podSchedulingEventListenerContext.podName
            )
            // todo : add available conditions matcher DSL
            if (conditions.all { it }) {
                throw TriggerException()
            }
        }) registerOn cxt.client
    }

    override fun run() {
        runListener(podSchedulingEventListenerContext)
    }
}
