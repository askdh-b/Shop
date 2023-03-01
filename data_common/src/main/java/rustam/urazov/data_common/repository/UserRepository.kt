package rustam.urazov.data_common.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.data_common.model.UserAuthModel
import rustam.urazov.data_common.model.UserModel

interface UserRepository {

    suspend fun signIn(user: UserModel): Either<Failure, Success>

    suspend fun logIn(userAuth: UserAuthModel): Either<Failure, Success>

}