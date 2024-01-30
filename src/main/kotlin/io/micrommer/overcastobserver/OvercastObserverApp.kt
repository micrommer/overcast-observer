package io.micrommer.overcastobserver

import io.fabric8.kubernetes.client.KubernetesClientBuilder
import io.micrommer.overcastobserver.control.Builder
import io.micrommer.overcastobserver.control.LivenessProbeWizardBlock
import io.micrommer.overcastobserver.control.LogStreamerWizardBlock
import io.micrommer.overcastobserver.domain.GenericK8sContext
import io.micrommer.overcastobserver.domain.expandToLiveProbeContext
import io.micrommer.overcastobserver.domain.expandToLogStreamerContext
import io.micrommer.overcastobserver.exception.SequenceEndedException
import kotlin.time.Duration.Companion.seconds

fun main() {
    val freshBuilder = {
        val builder = Builder()
        val cxt = GenericK8sContext("", 1.seconds, KubernetesClientBuilder().build())
        builder.withLogStreamer(LogStreamerWizardBlock(cxt.expandToLogStreamerContext("", listOf())))
        builder.withLivenessProbe(LivenessProbeWizardBlock(cxt.expandToLiveProbeContext("")))
        builder.build()
    }

    while (true) {
        val actionSeq = freshBuilder()
        runCatching { actionSeq.start() }.onFailure {
            when (it) {
                is SequenceEndedException -> {}
                else -> throw it
            }
        }
    }
}