package rustam.urazov.data_shop.repository

import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.data_shop_network.ShopApi
import rustam.urazov.data_shop_network.model.*
import rustam.urazov.data_shop.model.*
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi
) : ProductRepository {

    override suspend fun getLatest(): Either<Failure, LatestModel> =
        when (val result = shopApi.getLatest()) {
            is Either.Left -> Either.Left(result.a)
            is Either.Right -> Either.Right(map(result.b))
        }

    override suspend fun getFlashSale(): Either<Failure, FlashSaleModel> =
        when (val result = shopApi.getFlashSale()) {
            is Either.Left -> Either.Left(result.a)
            is Either.Right -> Either.Right(map(result.b))
        }

    override suspend fun getProductDetails(): Either<Failure, ProductDetailsModel> =
        when (val result = shopApi.getProductDetails()) {
            is Either.Left -> Either.Left(result.a)
            is Either.Right -> Either.Right(map(result.b))
        }

    private fun map(latestResponse: LatestResponse): LatestModel = LatestModel(
        latest = latestResponse.latest.map { map(it) }
    )

    private fun map(productResponse: ProductResponse): ProductModel = ProductModel(
        category = productResponse.category,
        name = productResponse.name,
        price = productResponse.price,
        imageUrl = productResponse.imageUrl
    )

    private fun map(flashSaleResponse: FlashSaleResponse): FlashSaleModel = FlashSaleModel(
        flashSale = flashSaleResponse.flashSale.map { map(it) }
    )

    private fun map(productOnSaleResponse: ProductOnSaleResponse): ProductOnSaleModel =
        ProductOnSaleModel(
            category = productOnSaleResponse.category,
            name = productOnSaleResponse.name,
            price = productOnSaleResponse.price,
            discount = productOnSaleResponse.discount,
            imageUrl = productOnSaleResponse.imageUrl
        )

    private fun map(productDetailsResponse: ProductDetailsResponse): ProductDetailsModel =
        ProductDetailsModel(
            name = productDetailsResponse.name,
            description = productDetailsResponse.description,
            rating = productDetailsResponse.rating,
            numberOfReviews = productDetailsResponse.numberOfReviews,
            price = productDetailsResponse.price,
            colors = productDetailsResponse.colors,
            imageUrls = productDetailsResponse.imageUrls
        )

}