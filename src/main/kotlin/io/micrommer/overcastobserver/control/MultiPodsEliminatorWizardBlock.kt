package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodEliminatorContext

class MultiPodsEliminatorWizardBlock(val multiPodEliminatorContext: PodEliminatorContext) : Action(multiPodEliminatorContext), MultiPodsEliminatorWizard {
    override fun run() {
        runPodEliminator(multiPodEliminatorContext)
    }

    override fun runPodEliminator(multiPodEliminatorContext: PodEliminatorContext) {

    }
}
