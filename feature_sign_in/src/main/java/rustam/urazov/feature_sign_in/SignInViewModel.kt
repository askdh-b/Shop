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

@HiltViewModel
class SignInViewModel(
    private val signIn: SignIn
)  : BaseViewModel() {

    private val mutableFirstName: MutableStateFlow<String> = MutableStateFlow(String.empty())
    val firstName: StateFlow<String> = mutableFirstName.asStateFlow()
    private val mutableLastName: MutableStateFlow<String> = MutableStateFlow(String.empty())
    val lastName: StateFlow<String> = mutableLastName.asStateFlow()
    private val mutableEmail: MutableStateFlow<String> = MutableStateFlow(String.empty())
    val email: StateFlow<String> = mutableEmail.asStateFlow()

    fun handleFirstName(firstName: String) {
        mutableFirstName.value = firstName
    }

    fun handleLastName(lastName: String) {
        mutableLastName.value = lastName
    }

    fun handleEmail(email: String) {
        mutableEmail.value = email
    }

    fun signIn(user: User) = signIn(SignIn.Params(user), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleSuccess
        )
    }

    private fun handleSuccess(success: Success) {

    }

}