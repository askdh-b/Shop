package rustam.urazov.feature_product_details

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.functional.fold
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.core.platform.BaseViewModel
import rustam.urazov.data_shop.model.ProductDetailsModel
import rustam.urazov.feature_product_details.model.ProductDetailsView
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetails
) : BaseViewModel() {

    private val mutableProductDetails: MutableStateFlow<ProductDetailsState> =
        MutableStateFlow(ProductDetailsState.NoData)
    val productDetails: StateFlow<ProductDetailsState> = mutableProductDetails.asStateFlow()

    fun getProductDetails() = getProductDetails(UseCase.None(), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleProductDetails
        )
    }

    private fun handleProductDetails(productDetailsModel: ProductDetailsModel) {
        mutableProductDetails.value = ProductDetailsState.ProductDetails(map(productDetailsModel))
    }

    private fun map(productDetailsModel: ProductDetailsModel): ProductDetailsView =
        ProductDetailsView(
            name = productDetailsModel.name,
            description = productDetailsModel.description,
            rating = productDetailsModel.rating,
            numberOfReviews = productDetailsModel.numberOfReviews,
            price = productDetailsModel.price,
            colors = productDetailsModel.colors,
            imageUrls = productDetailsModel.imageUrls
        )
}