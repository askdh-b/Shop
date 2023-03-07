package rustam.urazov.data_shop_network.model

import com.google.gson.annotations.SerializedName

data class ProductOnSaleResponse(
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Float,
    @SerializedName("discount") val discount: Int,
    @SerializedName("image_url") val imageUrl: String
)