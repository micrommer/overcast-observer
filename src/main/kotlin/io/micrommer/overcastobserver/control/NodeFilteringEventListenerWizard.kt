package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.NodeFilteringEventListenerContext

interface NodeFilteringEventListenerWizard {
    fun runListener(nodeFilteringEventListenerContext: NodeFilteringEventListenerContext)
}
