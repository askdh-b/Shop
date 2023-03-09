package rustam.urazov.feature_log_in.models

import rustam.urazov.data_common.model.UserAuthModel

data class UserAuthView(
    val firstName: String,
    val password: String
)

fun UserAuthView.map(): UserAuthModel = UserAuthModel(firstName, password)