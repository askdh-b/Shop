package rustam.urazov.shop.screens.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding { FragmentMainBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            val navController = fcvMain.getFragment<NavHostFragment>().navController
            bnvMain.setupWithNavController(navController)
            //bnvMain.setOnItemSelectedListener { true }
        }
    }
}