package rustam.urazov.shop.screens.signin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.extension.empty
import rustam.urazov.core.platform.BaseViewModel

class SignInViewModel : BaseViewModel() {

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

}