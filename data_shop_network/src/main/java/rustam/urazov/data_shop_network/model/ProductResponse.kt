package rustam.urazov.data_shop_network.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("image_url") val imageUrl: String
)