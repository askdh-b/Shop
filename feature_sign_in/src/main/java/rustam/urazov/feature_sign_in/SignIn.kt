package rustam.urazov.feature_sign_in

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.exception.Success
import rustam.urazov.core.functional.Either
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.data_common.model.UserModel
import rustam.urazov.data_common.repository.UserRepository
import javax.inject.Inject

class SignIn @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Success, SignIn.Params>() {

    override suspend fun run(params: Params): Either<Failure, Success> = userRepository.signIn(params.user)

    data class Params(val user: UserModel)

}