package io.micrommer.overcastobserver.domain

import io.fabric8.kubernetes.client.KubernetesClient
import kotlin.time.Duration

data class LiveProbeContext(
    override val namespace: String,
    override val updateInterval: Duration,
    override val client: KubernetesClient,
    val podName: String
) : GenericK8sContext(namespace, updateInterval, client)

fun GenericK8sContext.expandToLiveProbeContext(targetPod: String): LiveProbeContext =
    LiveProbeContext(this.namespace, this.updateInterval, this.client, targetPod)

