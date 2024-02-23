package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodSchedulingEventListenerContext

interface PodSchedulingEventListenerWizard {
    fun runListener(podSchedulingEventListenerContext: PodSchedulingEventListenerContext)
}
