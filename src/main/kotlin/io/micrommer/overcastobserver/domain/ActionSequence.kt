package io.micrommer.overcastobserver.domain

import io.micrommer.overcastobserver.control.Action
import io.micrommer.overcastobserver.exception.SequenceEndedException
import io.micrommer.overcastobserver.exception.TriggerException
import java.util.EmptyStackException
import java.util.Stack

data class ActionSequence(val seq: Stack<Action>) {
    fun start() {
        runCatching {
            seq.pop().run()
        }.onFailure {
            var ex = it
            val next = runCatching { seq.pop() }.onFailure { exception -> ex = exception }.getOrNull()
            when (ex) {
                is EmptyStackException -> throw SequenceEndedException()
                is TriggerException -> next!!.run()
                else -> throw ex
            }
        }
    }

}
