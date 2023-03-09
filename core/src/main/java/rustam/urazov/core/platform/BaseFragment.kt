package rustam.urazov.core.platform

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import rustam.urazov.core.R
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.extension.launchAndRepeatOnStart

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    abstract val viewModel: BaseViewModel

    fun <T> Flow<T>.collectWhileStarted(block: (T) -> Unit) {
        launchAndRepeatOnStart {
            collect { block(it) }
        }
    }

    private fun notifyWithAction(
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

    protected fun renderFailure(failure: Failure) {
        when (failure) {
            Failure.ConnectionError -> notifyWithAction(requireView(), "Please, check your network connection", R.string.ok, { viewModel.close() }, R.color.action_button_text)
            is Failure.MemoryError -> notifyWithAction(requireView(), failure.message, R.string.ok, { viewModel.close() }, R.color.action_button_text)
            Failure.NoError -> {}
            is Failure.ServerError -> notifyWithAction(requireView(), failure.message, R.string.ok, { viewModel.close() }, R.color.action_button_text)
            else -> notifyWithAction(requireView(), "Unexpected error", R.string.ok, { viewModel.close() }, R.color.action_button_text)
        }
    }

}