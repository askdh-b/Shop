package rustam.urazov.shop.screens.signin

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.extension.empty
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_sign_in.SignInViewModel
import rustam.urazov.feature_sign_in.models.UserView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentSignInBinding

@AndroidEntryPoint
class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private var viewBinding: FragmentSignInBinding? = null
    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentSignInBinding.bind(view)

        viewBinding?.etEmail?.addTextChangedListener {
            viewModel.handleEmail(viewBinding?.etEmail?.text.toString())
        }

        viewBinding?.bSignIn?.setOnClickListener {
            viewModel.signIn(UserView(
                firstName = viewBinding?.etFirstName?.text.toString(),
                lastName = viewBinding?.etLastName?.text.toString(),
                email = viewBinding?.etEmail?.text.toString(),
                password = String.empty()
            ))
        }

        lifecycleScope.launchWhenStarted {
            viewModel.emailValidationState.collect { error ->
                when (error.isEmpty()) {
                    true -> {
                        viewBinding?.etEmail?.setBackgroundResource(R.drawable.view_sign_text_field)
                    }
                    false -> {
                        viewBinding?.etEmail?.setBackgroundResource(R.drawable.view_sign_text_field_error)
                        viewBinding?.tvEmail?.text = error
                    }
                }
            }
        }
    }

}