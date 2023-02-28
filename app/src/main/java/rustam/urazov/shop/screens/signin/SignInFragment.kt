package rustam.urazov.shop.screens.signin

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.extension.empty
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_sign_in.SignInViewModel
import rustam.urazov.feature_sign_in.models.UserView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentSignInBinding

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewBinding by viewBinding { FragmentSignInBinding.bind(it) }
    private val viewModel by viewModels<SignInViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            etEmail.addTextChangedListener {
                viewModel.handleEmail(etEmail.text.toString())
            }

            bSignIn.setOnClickListener {
                viewModel.signIn(UserView(
                    firstName = etFirstName.text.toString(),
                    lastName = etLastName.text.toString(),
                    email = etEmail.text.toString(),
                    password = String.empty()
                ))
            }

            viewModel.emailValidationState.collectWhileStarted { error ->
                when (error.isEmpty()) {
                    true -> {
                        etEmail.setBackgroundResource(R.drawable.view_sign_text_field)
                    }
                    false -> {
                        etEmail.setBackgroundResource(R.drawable.view_sign_text_field_error)
                        tvEmail.text = error
                    }
                }
            }
        }

    }

}