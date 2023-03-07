package rustam.urazov.data_shop_network

import retrofit2.Retrofit
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.data_shop_network.model.*
import javax.inject.Inject

class ShopService @Inject constructor(
    retrofit: Retrofit
) : ShopApi {

    private val shopApi by lazy { retrofit.create(ShopApi::class.java) }

    override suspend fun getLatest(): Either<Failure, LatestResponse> = shopApi.getLatest()

    override suspend fun getFlashSale(): Either<Failure, FlashSaleResponse> =
        shopApi.getFlashSale()

    override suspend fun getProductDetails(): Either<Failure, ProductDetailsResponse> =
        shopApi.getProductDetails()

}