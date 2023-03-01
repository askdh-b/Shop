package rustam.urazov.data_common.model

import rustam.urazov.data_storage.model.UserAuth

data class UserAuthModel(
    val firstName: String,
    val lastName: String
)

fun UserAuthModel.map(): UserAuth = UserAuth(firstName, lastName)