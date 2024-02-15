package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodEliminatorContext
import io.micrommer.overcastobserver.exception.MalformedInstrumentException

class PodEliminatorWizardBlock(podEliminatorContext: PodEliminatorContext) : Action(podEliminatorContext), PodEliminatorWizard {
    override fun runPodEliminator(podEliminatorContext: PodEliminatorContext) {
        cxt.client.pods().inNamespace(cxt.namespace).list().items.first {
            it.fullResourceName == podEliminatorContext.targetPod
        }
    }

    override fun run() {
        TODO("Not yet implemented")
    }
}
