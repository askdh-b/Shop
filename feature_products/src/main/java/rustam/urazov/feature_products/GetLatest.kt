package rustam.urazov.feature_products

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.data_shop.model.LatestModel
import rustam.urazov.data_shop.repository.ProductRepository
import javax.inject.Inject

class GetLatest @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<LatestModel, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, LatestModel> =
        productRepository.getLatest()

}