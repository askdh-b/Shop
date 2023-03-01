package rustam.urazov.shop.screens.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_log_in.LogInViewModel
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.feature_log_in.models.UserAuthView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentLogInBinding

@AndroidEntryPoint
class LogInFragment : BaseFragment(R.layout.fragment_log_in) {

    private val viewBinding by viewBinding { FragmentLogInBinding.bind(it) }
    private val viewModel by viewModels<LogInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {

            bLogIn.setOnClickListener {
                viewModel.logIn(UserAuthView(
                    firstName = etFirstName.text.toString(),
                    password = etPassword.text.toString()
                ))
            }

            viewModel.failure.collectWhileStarted { state ->
                renderFailure(state)
            }
        }
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