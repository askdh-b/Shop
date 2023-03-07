package rustam.urazov.feature_products

import rustam.urazov.feature_products.model.ProductView

sealed class FlashSaleState {
    object NoData : FlashSaleState()
    data class FlashSale(val products: List<ProductView.ProductOnSaleView>) : FlashSaleState()
}