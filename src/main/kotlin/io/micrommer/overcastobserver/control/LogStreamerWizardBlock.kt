package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.client.dsl.internal.LogWatchCallback
import io.fabric8.kubernetes.client.dsl.internal.OperationContext
import io.micrommer.overcastobserver.domain.LogStreamerContext
import io.micrommer.overcastobserver.exception.TriggerException
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import kotlin.time.toJavaDuration

class LogStreamerWizardBlock(private val logStreamerContext: LogStreamerContext) :
    LogStreamerWizard,
    Action(logStreamerContext) {
    override fun runLogStreamer(logStreamerContext: LogStreamerContext) {
        logStreamerContext.client.pods().list().items.find {
            it.fullResourceName == logStreamerContext.targetPod
        }?.also {
            val os = ByteArrayOutputStream()
            LogWatchCallback(os, OperationContext().create(logStreamerContext.namespace, logStreamerContext.targetPod))

            //todo: better way to watch
//            val a = client.pods().withName("").watchLog()

            val reader = BufferedReader(InputStreamReader(ByteArrayInputStream(os.toByteArray())))
            reader.lines().forEach {
                if (logStreamerContext.triggers.any { regex -> regex.matches(it) })
                    throw TriggerException()
            }
        } ?: Thread.sleep(logStreamerContext.updateInterval.toJavaDuration())
        return runLogStreamer(logStreamerContext)
    }

    override fun run() {
        runLogStreamer(logStreamerContext)
    }

    private fun OperationContext.create(ns: String, podName: String): OperationContext {
        this.withNamespace(ns)
        this.withName(podName)
        return this
    }
}
