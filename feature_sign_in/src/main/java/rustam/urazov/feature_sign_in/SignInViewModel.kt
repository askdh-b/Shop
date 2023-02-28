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
import rustam.urazov.core.platform.EmailRule
import rustam.urazov.core.platform.LengthRule
import rustam.urazov.core.validation.Validate
import rustam.urazov.core.validation.ValidationResult
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

    val validateEmail = Validate.Builder<String>()
        .addValue(String.empty())
        .addRule(EmailRule())
        .addRule(LengthRule(5, 90))
        .build()

    fun handleEmail(email: String) {
        validateEmail.update(email)
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