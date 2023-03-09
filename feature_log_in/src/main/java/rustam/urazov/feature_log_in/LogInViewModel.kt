package rustam.urazov.feature_log_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.fold
import rustam.urazov.core.platform.BaseViewModel
import rustam.urazov.feature_log_in.models.UserAuthView
import rustam.urazov.feature_log_in.models.map
import javax.inject.Inject

@HiltViewModel
class LogInViewModel
@Inject constructor(
    private val logIn: LogIn
) : BaseViewModel() {

    private val mutableLogState: MutableStateFlow<Success> = MutableStateFlow(Success.Wait)
    val logState: StateFlow<Success> = mutableLogState.asStateFlow()

    fun logIn(user: UserAuthView) = logIn(LogIn.Params(user.map()), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleSuccess
        )
    }

    private fun handleSuccess(success: Success) {
        mutableLogState.value = success
    }

}