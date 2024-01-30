package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.api.model.ProbeBuilder
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import io.micrommer.overcastobserver.domain.GenericK8sContext
import io.micrommer.overcastobserver.domain.LiveProbeContext
import kotlin.time.Duration.Companion.seconds

class LivenessProbeWizardBlock(private val liveProbeContext: LiveProbeContext) :
    LivenessProbeWizard,
    Action(liveProbeContext) {
    override fun runLiveProbe(liveProbeContext: LiveProbeContext) {
        val podsList = liveProbeContext.client.pods().inNamespace(liveProbeContext.namespace).list().items
        podsList.first {
            it.fullResourceName == liveProbeContext.podName
        }.also {

        }


        val svcList = liveProbeContext.client.services().inNamespace("thingsboard").list().items
        svcList.forEach {

        }
        val probeBuilder = ProbeBuilder().withNewHttpGet()


    }

    override fun run() {
        runLiveProbe(liveProbeContext)
    }

}