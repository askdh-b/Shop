package rustam.urazov.feature_products.model

sealed class Product {
    object Search : Product()
    object CategorySection : Product()
    data class LatestSection(val latestProducts: List<ProductView.ProductWithoutSaleView>) :
        Product()
    data class FlashSaleSection(val flashSaleProducts: List<ProductView.ProductOnSaleView>) :
        Product()
}