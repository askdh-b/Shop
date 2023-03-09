package rustam.urazov.feature_log_out

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.data_common.repository.UserRepository
import javax.inject.Inject

class LogOut @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Success, LogOut.Params>() {

    override suspend fun run(params: Params): Either<Failure, Success> = userRepository.logOut(params.user)

    data class Params(val user: String)
}