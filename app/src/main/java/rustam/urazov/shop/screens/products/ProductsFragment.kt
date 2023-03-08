package rustam.urazov.shop.screens.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import rustam.urazov.core.extension.viewBinding
import rustam.urazov.core.platform.BaseFragment
import rustam.urazov.feature_products.FlashSaleState
import rustam.urazov.feature_products.LatestState
import rustam.urazov.feature_products.ProductsViewModel
import rustam.urazov.feature_products.model.Product
import rustam.urazov.feature_products.model.ProductView
import rustam.urazov.shop.R
import rustam.urazov.shop.adapters.*
import rustam.urazov.shop.databinding.FragmentProductsBinding
import rustam.urazov.shop.screens.main.MainFragment

@AndroidEntryPoint
class ProductsFragment : BaseFragment(R.layout.fragment_products) {

    private val viewBinding by viewBinding { FragmentProductsBinding.bind(it) }
    override val viewModel by viewModels<ProductsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLatest()
        viewModel.getFlashSale()

        val adapter = ListDelegationAdapter(
            searchAdapterDelegate(),
            categoriesAdapterDelegate(),
            latestProductsAdapterDelegate(),
            flashSaleProductsAdapterDelegate()
        )

        with(viewBinding) {
            with(((parentFragment as NavHostFragment).parentFragment as MainFragment).viewBinding.tbMain) {
                title = "Trade by bata"
                isTitleCentered = true
                setCollapseIcon(R.mipmap.ic_user)
            }

            viewModel.products.collectWhileStarted {
                val latest = mutableListOf<ProductView.ProductWithoutSaleView>()
                val flashSale = mutableListOf<ProductView.ProductOnSaleView>()

                it.forEach { product ->
                    when (product) {
                        is ProductView.ProductOnSaleView -> flashSale.add(product)
                        is ProductView.ProductWithoutSaleView -> latest.add(product)
                    }
                }

                if (latest.isNotEmpty() && flashSale.isNotEmpty()) {
                    viewModel.set(
                        listOf(
                            Product.Search,
                            Product.CategorySection,
                            Product.LatestSection(latest.toList()),
                            Product.FlashSaleSection(flashSale.toList())
                        )
                    )
                }
            }

            viewModel.productsFinal.collectWhileStarted {
                adapter.apply {
                    items = it
                    notifyDataSetChanged()
                }
            }

            viewModel.latestProducts.collectWhileStarted {
                when (it) {
                    is LatestState.Latest -> viewModel.setLatest(it.products)
                    LatestState.NoData -> {}
                }
            }

            viewModel.flashSaleProducts.collectWhileStarted {
                when (it) {
                    is FlashSaleState.FlashSale -> viewModel.setFlashSale(it.products)
                    FlashSaleState.NoData -> {}
                }
            }

            rvShop.adapter = adapter
        }
    }

}