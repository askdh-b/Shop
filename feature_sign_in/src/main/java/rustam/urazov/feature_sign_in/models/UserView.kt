package rustam.urazov.feature_sign_in.models

import rustam.urazov.data_common.model.User

data class UserView(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)

fun UserView.map(): User = User(this.firstName, this.lastName, this.email, this.password)