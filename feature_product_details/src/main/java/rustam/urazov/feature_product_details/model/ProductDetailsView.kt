package rustam.urazov.feature_product_details.model

data class ProductDetailsView(
    val name: String,
    val description: String,
    val rating: Float,
    val numberOfReviews: Int,
    val price: Int,
    val colors: List<String>,
    val imageUrls: List<String>
)