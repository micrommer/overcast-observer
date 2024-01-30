package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.LiveProbeContext

interface LiveProbeExpertise {
    fun runLiveProbe(liveProbeContext: LiveProbeContext)
}