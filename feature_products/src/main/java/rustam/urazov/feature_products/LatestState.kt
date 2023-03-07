package rustam.urazov.feature_products

import rustam.urazov.feature_products.model.ProductView

sealed class LatestState {
    object NoData : LatestState()
    data class Latest(val products: List<ProductView.ProductWithoutSaleView>) : LatestState()
}