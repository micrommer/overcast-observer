package io.micrommer.overcastobserver.domain

import io.fabric8.kubernetes.client.KubernetesClient
import kotlin.time.Duration

data class PodSchedulingEventListenerContext(
    override val namespace: String,
    override val updateInterval: Duration,
    override val client: KubernetesClient,
    val podName: String
) : GenericK8sContext(namespace, updateInterval, client)
