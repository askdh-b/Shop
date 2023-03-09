package rustam.urazov.shop.screens.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.exception.Success
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_log_out.ProfileViewModel
import rustam.urazov.shop.R
import rustam.urazov.shop.adapters.*
import rustam.urazov.shop.databinding.FragmentProfileBinding
import rustam.urazov.shop.screens.main.MainFragment

@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewBinding by viewBinding { FragmentProfileBinding.bind(it) }
    override val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListDelegationAdapter(
            profilePhotoAdapterDelegate(),
            smallActionTextAdapterDelegate(),
            fullNameAdapterDelegate(),
            actionButtonAdapterDelegate {},
            userActionWithDetailsAdapterDelegate(),
            userActionWithTextAdapterDelegate(),
            userActionAdapterDelegate()
        )

        with(viewBinding) {
            adapter.items = listOf(
                User.ProfilePhoto(R.mipmap.ic_user),
                User.SmallActionText(R.string.change_photo),
                User.FullName("Satria Adhi Pradana"),
                User.ActionButton(R.drawable.upload, R.string.upload_item),
                User.UserActionWithDetails(R.drawable.credit_card, R.string.trade_store),
                User.UserActionWithDetails(R.drawable.credit_card, R.string.payment_method),
                User.UserActionWithText(R.drawable.credit_card, R.string.balance, "$1593"),
                User.UserActionWithDetails(R.drawable.credit_card, R.string.trade_history),
                User.UserActionWithDetails(R.drawable.refresh, R.string.restore_purchase),
                User.UserAction(R.drawable.info, R.string.help) {},
                User.UserAction(R.drawable.exit, R.string.log_out) {
                    val firstName = ((parentFragment as NavHostFragment).parentFragment as MainFragment).firstName.orEmpty()
                    viewModel.logOut(firstName)
                },
            )

            with(((parentFragment as NavHostFragment).parentFragment as MainFragment).viewBinding.tbMain) {
                title = "Profile"
                isTitleCentered = true
            }

            rvUserProfile.adapter = adapter

            viewModel.logState.collectWhileStarted {
                when (it) {
                    Success.True -> ((parentFragment as NavHostFragment).parentFragment as MainFragment).findNavController()
                        .navigate(R.id.actionMainToSignIn)
                    Success.Wait -> {}
                }
            }
        }
    }

}