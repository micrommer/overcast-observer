package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.api.model.ProbeBuilder
import io.fabric8.kubernetes.client.KubernetesClientBuilder
import io.micrommer.overcastobserver.domain.LiveProbeContext


class K8sController: LiveProbeExpertise {
    override fun runLiveProbe(liveProbeContext: LiveProbeContext) {
        val client = KubernetesClientBuilder().build()
        val podsList = client.pods().inNamespace(liveProbeContext.namespace).list().items
        podsList.first {
            it.fullResourceName == liveProbeContext.podName
        }.also {

        }



        val svcList = client.services().inNamespace("thingsboard").list().items
        svcList.forEach {

        }
        val probeBuilder = ProbeBuilder().withNewHttpGet()

    }
}