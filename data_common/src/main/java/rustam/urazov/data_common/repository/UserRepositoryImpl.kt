package rustam.urazov.data_common.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.data_common.model.User
import rustam.urazov.data_storage.dao.UsersDao
import rustam.urazov.data_storage.entity.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao
) : UserRepository {

    override suspend fun signIn(user: User): Either<Failure, Success> =
        when (isExists(user.email)) {
            true -> Either.Left(Failure.MemoryError("This email address is already taken"))
            false -> Either.Right(Success())
        }


    private suspend fun isExists(email: String): Boolean =
        when (getUsers(email).size) {
            0 -> false
            else -> true
        }

    private suspend fun getUsers(email: String): List<UserEntity> = usersDao.selectByEmail(email)

}