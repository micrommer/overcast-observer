package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.GenericK8sContext

abstract class Action(val cxt: GenericK8sContext) : Runnable
