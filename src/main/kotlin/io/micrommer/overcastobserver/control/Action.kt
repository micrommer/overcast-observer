package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.GenericK8sContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.log

abstract class Action(val cxt: GenericK8sContext) : Runnable {
    protected val logger = LoggerFactory.getLogger(this.javaClass)
    protected fun logHeader() = "[${this.javaClass.simpleName}] :"

    protected fun logException(ex: Exception) =
        logger.error("${logHeader()} has has failed because of [${ex.javaClass.simpleName}] with message -> ${ex.message}")
}
