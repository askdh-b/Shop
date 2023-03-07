package rustam.urazov.core.platform

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import rustam.urazov.core.R
import rustam.urazov.core.extension.launchAndRepeatOnStart

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block(it) }
        }
    }

    fun notifyWithAction(
        view: View,
        message: String,
        @StringRes actionText: Int,
        action: () -> Any,
        @ColorRes color: Int,
    ) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(actionText) { action() }
        snackbar.setActionTextColor(resources.getColor(color))
        snackbar.show()
    }

}