package rustam.urazov.core.platform

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.Flow
import rustam.urazov.core.extension.launchAndRepeatOnStart

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block(it) }
        }
    }

}