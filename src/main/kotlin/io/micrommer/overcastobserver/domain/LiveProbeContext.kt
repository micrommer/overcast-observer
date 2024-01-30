package io.micrommer.overcastobserver.domain

data class LiveProbeContext(override val namespace: String, val podName: String) : GenericK8sContext(namespace)