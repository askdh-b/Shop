package rustam.urazov.feature_products.model

sealed class ProductView {
    data class ProductOnSaleView(
        val category: String,
        val name: String,
        val price: Float,
        val discount: Int,
        val imageUrl: String
    ) : ProductView()
    data class ProductWithoutSaleView(
        val category: String,
        val name: String,
        val price: Int,
        val imageUrl: String
    ) : ProductView()
}