package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.LiveProbeContext

interface LivenessProbeWizard {
    fun runLiveProbe(liveProbeContext: LiveProbeContext)
}