package rustam.urazov.data_shop.model

data class ProductOnSaleModel(
    val category: String,
    val name: String,
    val price: Float,
    val discount: Int,
    val imageUrl: String
)