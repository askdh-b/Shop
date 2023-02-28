package rustam.urazov.core.platform

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val bindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(OnCreateEventObserver(fragment) {
            binding = null
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw java.lang.IllegalStateException("Should not initialize binding when fragment view are destroyed")
        }

        return bindingFactory(thisRef.requireView())
            .also { this@FragmentBindingDelegate.binding = it }
    }

}