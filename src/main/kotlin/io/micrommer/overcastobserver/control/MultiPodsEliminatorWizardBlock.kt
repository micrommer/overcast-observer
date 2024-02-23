package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodEliminatorContext

class MultiPodsEliminatorWizardBlock(private val multiPodEliminatorContext: PodEliminatorContext) :
    Action(multiPodEliminatorContext), MultiPodsEliminatorWizard {
    override fun runPodEliminator(multiPodEliminatorContext: PodEliminatorContext) {

    }

    override fun run() {
        runPodEliminator(multiPodEliminatorContext)
    }

}
