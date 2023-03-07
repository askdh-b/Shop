package rustam.urazov.data_shop.model

data class ProductDetailsModel(
    val name: String,
    val description: String,
    val rating: Float,
    val numberOfReviews: Int,
    val price: Int,
    val colors: List<String>,
    val imageUrls: List<String>
)