package rustam.urazov.data_common.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.data_common.model.UserAuthModel
import rustam.urazov.data_common.model.UserModel
import rustam.urazov.data_common.model.map
import rustam.urazov.data_storage.dao.UsersDao
import rustam.urazov.data_storage.entity.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao
) : UserRepository {

    override suspend fun signIn(user: UserModel): Either<Failure, Success> =
        when (emailIsExists(user.email, user.firstName)) {
            true -> Either.Left(Failure.MemoryError("This email address or firstname is already taken"))
            false -> {
                insertUser(user.map())
                Either.Right(Success.True)
            }
        }

    override suspend fun logIn(userAuth: UserAuthModel): Either<Failure, Success> =
        when (isLogged(userAuth)) {
            true -> Either.Right(Success.True)
            false -> Either.Left(Failure.MemoryError("Invalid firstname or password"))
        }

    private suspend fun emailIsExists(email: String, firstName: String): Boolean =
        when (getUsersByEmail(email, firstName).size) {
            0 -> false
            else -> true
        }

    private suspend fun isLogged(userAuth: UserAuthModel): Boolean =
        when (auth(userAuth).size) {
            0 -> false
            else -> true
        }

    private suspend fun getUsersByEmail(email: String, firstName: String): List<UserEntity> =
        usersDao.selectByEmailOrFirstName(email, firstName)

    private suspend fun auth(userAuth: UserAuthModel) = usersDao.auth(userAuth.firstName, userAuth.password)

    private suspend fun insertUser(user: UserEntity) = usersDao.insertUser(user)

}