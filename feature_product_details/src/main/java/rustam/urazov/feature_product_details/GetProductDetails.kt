package rustam.urazov.feature_product_details

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.data_shop.model.ProductDetailsModel
import rustam.urazov.data_shop.repository.ProductRepository
import javax.inject.Inject

class GetProductDetails @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<ProductDetailsModel, UseCase.None>() {

    override suspend fun run(params: None): Either<Failure, ProductDetailsModel> =
        productRepository.getProductDetails()

}