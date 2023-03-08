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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val navController = fcvMain.getFragment<NavHostFragment>().navController
            bnvMain.setupWithNavController(navController)
            //bnvMain.setOnItemSelectedListener { true }
        }
    }
}