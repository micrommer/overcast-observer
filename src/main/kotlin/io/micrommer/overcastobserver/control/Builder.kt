package io.micrommer.overcastobserver.control

import io.micrommer.overcastobserver.domain.ActionSequence
import java.util.*

class Builder {

    private val stack = Stack<Action>()
    private fun add(any: Any): Builder = stack.add(any as Action).run { this@Builder }

    fun withLivenessProbe(livenessProbeWizard: LivenessProbeWizard): Builder = add(livenessProbeWizard)

    fun withLogStreamer(logStreamerWizard: LogStreamerWizard): Builder = add(logStreamerWizard)

    fun withPodEliminator(podEliminatorWizard: PodEliminatorWizard): Builder = add(podEliminatorWizard)

    fun withMultiplePodEliminator(multiplePodEliminatorWizard: PodEliminatorWizard): Builder =
        add(multiplePodEliminatorWizard)

    fun build(): ActionSequence = ActionSequence(stack)

}
