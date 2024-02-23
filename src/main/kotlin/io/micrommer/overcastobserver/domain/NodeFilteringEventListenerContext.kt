package io.micrommer.overcastobserver.domain

import io.fabric8.kubernetes.client.KubernetesClient
import kotlin.time.Duration

class NodeFilteringEventListenerContext (
    override val namespace: String,
    override val updateInterval: Duration,
    override val client: KubernetesClient,
    val nodeName: String
) : GenericK8sContext(namespace, updateInterval, client)
