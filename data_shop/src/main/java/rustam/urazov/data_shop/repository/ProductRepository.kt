package rustam.urazov.data_shop.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.data_shop.model.FlashSaleModel
import rustam.urazov.data_shop.model.LatestModel
import rustam.urazov.data_shop.model.ProductDetailsModel

interface ProductRepository {

    suspend fun getLatest(): Either<Failure, LatestModel>

    suspend fun getFlashSale(): Either<Failure, FlashSaleModel>

    suspend fun getProductDetails(): Either<Failure, ProductDetailsModel>

}