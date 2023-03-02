package rustam.urazov.shop.adapters

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import rustam.urazov.shop.databinding.LayoutActionTextSmallBinding
import rustam.urazov.shop.databinding.LayoutButtonBinding
import rustam.urazov.shop.databinding.LayoutProfilePhotoBinding
import rustam.urazov.shop.databinding.LayoutTextMediumBinding
import rustam.urazov.shop.databinding.LayoutUserActionBinding
import rustam.urazov.shop.databinding.LayoutUserActionWithDetailsBinding
import rustam.urazov.shop.databinding.LayoutUserActionWithTextBinding

fun profilePhotoAdapterDelegate() =
    adapterDelegateViewBinding<User.ProfilePhoto, User, LayoutProfilePhotoBinding>(
        { layoutInflater, parent ->
            LayoutProfilePhotoBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.ivUser.setImageResource(item.icon)
        }
    }

fun smallActionTextAdapterDelegate() =
    adapterDelegateViewBinding<User.SmallActionText, User, LayoutActionTextSmallBinding>(
        { layoutInflater, parent ->
            LayoutActionTextSmallBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.tvText.setText(item.text)
        }
    }

fun fullNameAdapterDelegate() =
    adapterDelegateViewBinding<User.FullName, User, LayoutTextMediumBinding>(
        { layoutInflater, parent ->
            LayoutTextMediumBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.tvText.text = item.text
        }
    }

fun actionButtonAdapterDelegate() =
    adapterDelegateViewBinding<User.ActionButton, User, LayoutButtonBinding>(
        { layoutInflater, parent ->
            LayoutButtonBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.bUploadItem.setIconResource(item.icon)
            binding.bUploadItem.setText(item.text)
        }
    }

fun userActionWithDetailsAdapterDelegate() =
    adapterDelegateViewBinding<User.UserActionWithDetails, User, LayoutUserActionWithDetailsBinding>(
        { layoutInflater, parent ->
            LayoutUserActionWithDetailsBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvTitle.setText(item.title)
        }
    }

fun userActionWithTextAdapterDelegate() =
    adapterDelegateViewBinding<User.UserActionWithText, User, LayoutUserActionWithTextBinding>(
        { layoutInflater, parent ->
            LayoutUserActionWithTextBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvTitle.setText(item.title)
            binding.tvText.text = item.text
        }
    }

fun userActionAdapterDelegate() =
    adapterDelegateViewBinding<User.UserAction, User, LayoutUserActionBinding>(
        { layoutInflater, parent ->
            LayoutUserActionBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.ivIcon.setImageResource(item.icon)
            binding.tvTitle.setText(item.title)
        }
    }

sealed class User {
    data class ProfilePhoto(@DrawableRes val icon: Int) : User()
    data class SmallActionText(@StringRes val text: Int) : User()
    data class FullName(val text: String) : User()
    data class ActionButton(@DrawableRes val icon: Int, @StringRes val text: Int) : User()
    data class UserActionWithDetails(@DrawableRes val icon: Int, @StringRes val title: Int) : User()
    data class UserActionWithText(
        @DrawableRes val icon: Int,
        @StringRes val title: Int,
        val text: String
    ) : User()

    data class UserAction(@DrawableRes val icon: Int, @StringRes val title: Int) : User()
}