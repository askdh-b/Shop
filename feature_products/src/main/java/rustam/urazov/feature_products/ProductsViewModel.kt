package rustam.urazov.feature_products

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.functional.fold
import rustam.urazov.core.interactor.UseCase
import rustam.urazov.core.platform.BaseViewModel
import rustam.urazov.data_shop.model.FlashSaleModel
import rustam.urazov.data_shop.model.LatestModel
import rustam.urazov.data_shop.model.ProductModel
import rustam.urazov.data_shop.model.ProductOnSaleModel
import rustam.urazov.feature_products.model.Product
import rustam.urazov.feature_products.model.ProductView
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getLatest: GetLatest,
    private val getFlashSale: GetFlashSale
) : BaseViewModel() {

    private val mutableLatestProducts: MutableStateFlow<LatestState> =
        MutableStateFlow(LatestState.NoData)
    val latestProducts: StateFlow<LatestState> = mutableLatestProducts.asStateFlow()

    private val mutableFlashSaleProducts: MutableStateFlow<FlashSaleState> =
        MutableStateFlow(FlashSaleState.NoData)
    val flashSaleProducts: StateFlow<FlashSaleState> = mutableFlashSaleProducts.asStateFlow()

    private val mutableProducts: MutableStateFlow<List<ProductView>> =
        MutableStateFlow(emptyList())
    val products: StateFlow<List<ProductView>> = mutableProducts.asStateFlow()

    private val mutableProductsFinal: MutableStateFlow<List<Product>> =
        MutableStateFlow(listOf(Product.Search, Product.CategorySection))
    val productsFinal: StateFlow<List<Product>> = mutableProductsFinal.asStateFlow()

    fun set(products: List<Product>) {
        mutableProductsFinal.value = products
    }

    fun setLatest(latest: List<ProductView.ProductWithoutSaleView>) {
        mutableProducts.value = mutableProducts.value + latest
    }

    fun setFlashSale(flashSale: List<ProductView.ProductOnSaleView>) {
        mutableProducts.value = mutableProducts.value + flashSale
    }

    fun getLatest() = getLatest(UseCase.None(), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleLatest
        )
    }

    fun getFlashSale() = getFlashSale(UseCase.None(), viewModelScope) {
        it.fold(
            ::handleFailure,
            ::handleFlashSale
        )
    }

    private fun handleLatest(latestModel: LatestModel) {
        mutableLatestProducts.value = LatestState.Latest(latestModel.latest.map { map(it) })
    }

    private fun handleFlashSale(flashSaleModel: FlashSaleModel) {
        mutableFlashSaleProducts.value =
            FlashSaleState.FlashSale(flashSaleModel.flashSale.map { map(it) })
    }

    private fun map(productModel: ProductModel): ProductView.ProductWithoutSaleView =
        ProductView.ProductWithoutSaleView(
            category = productModel.category,
            name = productModel.name,
            price = productModel.price,
            imageUrl = productModel.imageUrl
        )

    private fun map(productOnSaleModel: ProductOnSaleModel): ProductView.ProductOnSaleView =
        ProductView.ProductOnSaleView(
            category = productOnSaleModel.category,
            name = productOnSaleModel.name,
            price = productOnSaleModel.price,
            discount = productOnSaleModel.discount,
            imageUrl = productOnSaleModel.imageUrl
        )

}