package rustam.urazov.core.platform

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import rustam.urazov.core.extension.observe

class OnCreateEventObserver(
    private val fragment: Fragment,
    private val invoke: () -> Unit
) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            fragment.observe(fragment.viewLifecycleOwnerLiveData) { lifecycleOwner ->
                lifecycleOwner.lifecycle.addObserver(OnDestroyEventObserver(invoke))
            }
        }
    }

}