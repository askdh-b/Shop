package rustam.urazov.data_common.repository

import rustam.urazov.data_common.model.User

interface UserRepository {

    fun signIn(user: User)

}