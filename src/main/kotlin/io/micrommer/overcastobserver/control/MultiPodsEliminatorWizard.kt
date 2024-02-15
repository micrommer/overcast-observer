package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodEliminatorContext

interface MultiPodsEliminatorWizard {
    fun runPodEliminator(multiPodEliminatorContext: PodEliminatorContext)

}
