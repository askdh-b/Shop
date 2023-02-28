package rustam.urazov.shop.screens.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var viewBinding: FragmentSignInBinding? = null
    private var viewModel: SignInViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSignInBinding.bind(view)

        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]

    }

}