package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.LogStreamerContext

interface LogStreamerWizard {
    fun runLogStreamer(logStreamerContext: LogStreamerContext)

}