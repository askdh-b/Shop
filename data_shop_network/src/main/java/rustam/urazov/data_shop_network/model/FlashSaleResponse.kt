package rustam.urazov.data_shop_network.model

import com.google.gson.annotations.SerializedName

data class FlashSaleResponse(
    @SerializedName("flash_sale") val flashSale: List<ProductOnSaleResponse>
)