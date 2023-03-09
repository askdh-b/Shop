package rustam.urazov.shop.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    val viewBinding by viewBinding { FragmentMainBinding.bind(it) }
    var firstName: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            firstName = arguments?.getString(FIRST_NAME_KEY)
            val navController = fcvMain.getFragment<NavHostFragment>().navController
            bnvMain.setupWithNavController(navController)
        }
    }

    companion object {
        const val FIRST_NAME_KEY = "first_name"
    }
}