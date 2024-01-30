package io.micrommer.overcastobserver.domain

import io.fabric8.kubernetes.client.KubernetesClient
import kotlin.time.Duration

data class LogStreamerContext(
    override val namespace: String,
    override val updateInterval: Duration,
    override val client: KubernetesClient,
    val targetPod: String,
    val triggers: List<Regex>
) : GenericK8sContext(namespace, updateInterval, client)

fun GenericK8sContext.expandToLogStreamerContext(targetPod: String, triggers: List<Regex>): LogStreamerContext =
    LogStreamerContext(this.namespace, this.updateInterval, this.client, targetPod, triggers)