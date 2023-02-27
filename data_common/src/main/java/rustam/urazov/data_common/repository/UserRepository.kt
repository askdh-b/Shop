package rustam.urazov.data_common.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.data_common.model.User

interface UserRepository {

    suspend fun signIn(user: User): Either<Failure, Success>

}