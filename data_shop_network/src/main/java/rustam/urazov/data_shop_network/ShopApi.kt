package rustam.urazov.data_shop_network

import retrofit2.http.GET
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import rustam.urazov.data_shop_network.model.FlashSaleResponse
import rustam.urazov.data_shop_network.model.LatestResponse
import rustam.urazov.data_shop_network.model.ProductDetailsResponse

interface ShopApi {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): Either<Failure, LatestResponse>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): Either<Failure, FlashSaleResponse>

    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getProductDetails(): Either<Failure, ProductDetailsResponse>

}