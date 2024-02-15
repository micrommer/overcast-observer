package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.PodEliminatorContext

interface PodEliminatorWizard {
    fun runPodEliminator(podEliminatorContext: PodEliminatorContext)
}
