package rustam.urazov.shop.screens.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.exception.Success
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_log_in.LogInViewModel
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.feature_log_in.models.UserAuthView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentLogInBinding

@AndroidEntryPoint
class LogInFragment : BaseFragment(R.layout.fragment_log_in) {

    private val viewBinding by viewBinding { FragmentLogInBinding.bind(it) }
    override val viewModel by viewModels<LogInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {

            bLogIn.setOnClickListener {
                viewModel.logIn(UserAuthView(
                    firstName = etFirstName.text.toString(),
                    password = etPassword.text.toString()
                ))
            }

            ivEye.bringToFront()
            ivEye.setOnClickListener {
                if (etPassword.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD + InputType.TYPE_CLASS_TEXT) {
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD + InputType.TYPE_CLASS_TEXT
                    etPassword.setSelection(etPassword.length())
                    ivEye.setImageResource(R.drawable.eye_off_24)
                } else if (etPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD + InputType.TYPE_CLASS_TEXT) {
                    etPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD + InputType.TYPE_CLASS_TEXT
                    etPassword.setSelection(etPassword.length())
                    ivEye.setImageResource(R.drawable.eye_24)
                }
            }

            viewModel.failure.collectWhileStarted {
                renderFailure(it)
            }

            viewModel.logState.collectWhileStarted {
                when (it) {
                    Success.True -> findNavController().navigate(
                        R.id.actionLogInToMain,
                        null,
                        navOptions {
                            launchSingleTop = true
                            popUpTo(R.id.nav_graph_shop) {
                                inclusive = true
                            }
                        }
                    )
                    Success.Wait -> {}
                }
            }
        }
    }

}