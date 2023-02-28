package rustam.urazov.feature_sign_in

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.exception.Success
import rustam.urazov.core.extension.empty
import rustam.urazov.core.functional.fold
import rustam.urazov.core.platform.BaseViewModel
import rustam.urazov.data_common.model.User
import rustam.urazov.feature_sign_in.models.UserView
import rustam.urazov.feature_sign_in.models.map
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
@Inject constructor(
    private val signIn: SignIn
) : BaseViewModel() {

    private val mutableSignState: MutableStateFlow<Success> = MutableStateFlow(Success.Wait)
    val signState: StateFlow<Success> = mutableSignState.asStateFlow()

    private val mutableEmail: MutableStateFlow<String> = MutableStateFlow(String.empty())
    val email: StateFlow<String> = mutableEmail.asStateFlow()
    private val mutableEmailValidationState: MutableStateFlow<String> =
        MutableStateFlow(String.empty())
    val emailValidationState: StateFlow<String> = mutableEmailValidationState.asStateFlow()

    fun handleEmail(email: String) {
        mutableEmail.value = email
        handleEmailValidation(validateEmail(email))
    }

    private fun handleEmailValidation(isValid: Boolean) {
        when (isValid) {
            true -> mutableEmailValidationState.value = String.empty()
            false -> mutableEmailValidationState.value = "Invalid email"
        }
    }

    private fun validateEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        return regex.containsMatchIn(email)
    }

    fun signIn(user: UserView) = signIn(SignIn.Params(user.map()), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleSuccess
        )
    }

    private fun handleSuccess(success: Success) {
        mutableSignState.value = success
    }

}