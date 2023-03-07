package rustam.urazov.data_shop_network.model

import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("number_of_reviews") val numberOfReviews: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("colors") val colors: List<String>,
    @SerializedName("image_urls") val imageUrls: List<String>
)