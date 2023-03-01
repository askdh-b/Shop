package rustam.urazov.shop.screens.signin

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.extension.empty
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.core.validation.ValidationResult
import rustam.urazov.feature_sign_in.SignInViewModel
import rustam.urazov.feature_sign_in.models.UserView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentSignInBinding
import rustam.urazov.shop.screens.login.LogInFragment

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding { FragmentSignInBinding.bind(it) }
    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            etFirstName.addTextChangedListener {
                viewModel.handleFirstName(etFirstName.text.toString())
            }

            etLastName.addTextChangedListener {
                viewModel.handleLastName(etLastName.text.toString())
            }

            etEmail.addTextChangedListener {
                viewModel.handleEmail(etEmail.text.toString())
            }

            etPassword.addTextChangedListener {
                viewModel.handlePassword(etPassword.text.toString())
            }

            bSignIn.setOnClickListener {
                viewModel.signIn(
                    UserView(
                        firstName = etFirstName.text.toString(),
                        lastName = etLastName.text.toString(),
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString()
                    )
                )
            }

            viewModel.validateFirstName.result.collectWhileStarted { state ->
                handleValidationResult(state, etFirstName, tvFirstName)
            }

            viewModel.validateLastName.result.collectWhileStarted { state ->
                handleValidationResult(state, etLastName, tvLastName)
            }

            viewModel.validateEmail.result.collectWhileStarted { state ->
                handleValidationResult(state, etEmail, tvEmail)
            }

            viewModel.validatePassword.result.collectWhileStarted { state ->
                handleValidationResult(state, etPassword, tvPassword)
            }

            viewModel.failure.collectWhileStarted { state ->
                renderFailure(state)
            }

            tvLogIn.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, LogInFragment())
                    .commitAllowingStateLoss()
            }

        }

    }

    private fun handleValidationResult(
        result: ValidationResult,
        editText: AppCompatEditText,
        textView: AppCompatTextView
    ) {
        handleValidationResult(
            result,
            { setFieldState(editText, textView, R.drawable.view_sign_text_field_error, it) },
            { setFieldState(editText, textView) }
        )
    }

    private fun handleValidationResult(
        result: ValidationResult,
        isInvalid: (String) -> Unit,
        isValid: () -> Unit
    ) {
        when (result) {
            is ValidationResult.Invalid -> isInvalid(result.message)
            ValidationResult.Valid -> isValid()
        }
    }

    private fun setFieldState(
        editText: AppCompatEditText,
        textView: AppCompatTextView,
        @DrawableRes resId: Int = R.drawable.view_sign_text_field,
        text: String = String.empty()
    ) {
        editText.setBackgroundResource(resId)
        textView.text = text
    }

    private fun renderFailure(failure: Failure) {
        when (failure) {
            Failure.ConnectionError -> notifyWithAction(requireView(), R.string.connection_error, R.string.ok, {  }, R.color.white2)
            is Failure.MemoryError -> notifyWithAction(requireView(), R.string.connection_error, R.string.ok, {  }, R.color.white2)
            Failure.NoError -> {}
            is Failure.ServerError -> notifyWithAction(requireView(), R.string.connection_error, R.string.ok, {  }, R.color.white2)
        }
    }
}