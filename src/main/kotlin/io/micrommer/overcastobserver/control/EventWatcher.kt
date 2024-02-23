package io.micrommer.overcastobserver.control

import io.fabric8.kubernetes.api.model.events.v1.Event
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.Watch
import io.fabric8.kubernetes.client.Watcher
import io.fabric8.kubernetes.client.WatcherException

class EventWatcher(
    val onEventReceived: (action: Watcher.Action, event: Event) -> Unit,
    val onClosed: (cause: WatcherException?) -> Unit = {}
) : Watcher<Event> {
    override fun eventReceived(action: Watcher.Action, event: Event) {
        onEventReceived(action, event)
    }

    override fun onClose(cause: WatcherException?) {
        onClosed(cause)
    }

    infix fun registerOn(kubernetesClient: KubernetesClient): Watch =
        kubernetesClient.events().v1().events().watch(this)
}
