package rustam.urazov.shop.screens.profile

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.shop.R
import rustam.urazov.shop.adapters.*
import rustam.urazov.shop.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewBinding by viewBinding { FragmentProfileBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListDelegationAdapter<List<User>>(
            profilePhotoAdapterDelegate(),
            smallActionTextAdapterDelegate(),
            fullNameAdapterDelegate(),
            actionButtonAdapterDelegate(),
            userActionWithDetailsAdapterDelegate(),
            userActionWithTextAdapterDelegate(),
            userActionAdapterDelegate()
        )

        adapter.items = listOf(
            User.ProfilePhoto(R.mipmap.ic_user),
            User.SmallActionText(R.string.change_photo),
            User.FullName("Satria Adhi Pradana"),
            User.ActionButton(R.drawable.upload, R.string.upload_item),
            User.UserActionWithDetails(R.drawable.credit_card, R.string.trade_store),
            User.UserActionWithDetails(R.drawable.credit_card, R.string.payment_method),
            User.UserActionWithText(R.drawable.credit_card, R.string.balance, "$1593"),
            User.UserActionWithDetails(R.drawable.credit_card, R.string.trade_history),
            User.UserActionWithDetails(R.drawable.credit_card, R.string.restore_purchase),
            User.UserAction(R.drawable.credit_card, R.string.help),
            User.UserAction(R.drawable.credit_card, R.string.log_out),
        )

        with(viewBinding) {
            rvUserProfile.adapter = adapter
        }
    }

}