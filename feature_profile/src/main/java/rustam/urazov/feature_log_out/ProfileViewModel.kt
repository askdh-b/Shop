package rustam.urazov.feature_log_out

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.fold
import rustam.urazov.core.platform.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val logOut: LogOut) : BaseViewModel() {

    private val mutableLogState: MutableStateFlow<Success> = MutableStateFlow(Success.Wait)
    val logState: StateFlow<Success> = mutableLogState.asStateFlow()

    fun logOut(user: String) = logOut(LogOut.Params(user), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleSuccess
        )
    }

    private fun handleSuccess(success: Success) {
        mutableLogState.value = success
    }

}