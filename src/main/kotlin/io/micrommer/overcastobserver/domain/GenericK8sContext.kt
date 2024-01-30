package io.micrommer.overcastobserver.domain

import io.fabric8.kubernetes.client.KubernetesClient
import kotlin.time.Duration

open class GenericK8sContext(open val namespace: String, open val updateInterval: Duration, open val client: KubernetesClient)