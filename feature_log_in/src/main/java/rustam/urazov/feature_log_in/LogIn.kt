package rustam.urazov.feature_log_in

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.data_common.model.UserAuthModel
import rustam.urazov.data_common.repository.UserRepository
import javax.inject.Inject

class LogIn @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Success, LogIn.Params>() {

    override suspend fun run(params: Params): Either<Failure, Success> =
        userRepository.logIn(params.userAuth)

    data class Params(val userAuth: UserAuthModel)
}