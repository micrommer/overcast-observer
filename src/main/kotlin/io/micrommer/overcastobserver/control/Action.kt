package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.client.KubernetesClient
import io.micrommer.overcastobserver.domain.GenericK8sContext

abstract class Action(val cxt: GenericK8sContext) : Runnable