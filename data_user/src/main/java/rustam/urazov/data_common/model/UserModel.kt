package rustam.urazov.data_common.model

import rustam.urazov.data_storage.entity.UserEntity

data class UserModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

fun UserModel.map(): UserEntity = UserEntity(
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password
)