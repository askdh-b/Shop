package rustam.urazov.feature_product_details

import rustam.urazov.feature_product_details.model.ProductDetailsView

sealed class ProductDetailsState {
    object NoData : ProductDetailsState()
    data class ProductDetails(val productDetails: ProductDetailsView) : ProductDetailsState()
}