package rustam.urazov.shop.adapters

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import rustam.urazov.feature_products.model.Brand
import rustam.urazov.feature_products.model.Product
import rustam.urazov.feature_products.model.ProductView
import rustam.urazov.shop.R
import rustam.urazov.shop.databinding.LayoutBrandsBinding
import rustam.urazov.shop.databinding.LayoutCategoriesBinding
import rustam.urazov.shop.databinding.LayoutFlashSaleBinding
import rustam.urazov.shop.databinding.LayoutLatestBinding
import rustam.urazov.shop.databinding.LayoutSearchBinding
import rustam.urazov.shop.databinding.ViewBrandBinding
import rustam.urazov.shop.databinding.ViewCategoryBinding
import rustam.urazov.shop.databinding.ViewFlashSaleBinding
import rustam.urazov.shop.databinding.ViewLatestBinding

fun searchAdapterDelegate() =
    adapterDelegateViewBinding<Product.Search, Product, LayoutSearchBinding>(
        { layoutInflater, parent ->
            LayoutSearchBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {

        }
    }

fun categoriesAdapterDelegate() =
    adapterDelegateViewBinding<Product.CategorySection, Product, LayoutCategoriesBinding>(
        { layoutInflater, parent ->
            LayoutCategoriesBinding.inflate(layoutInflater, parent, false).apply {
                rvCategories.adapter = ListDelegationAdapter(categoryAdapterDelegate())
            }
        }
    ) {
        bind {
            (binding.rvCategories.adapter as ListDelegationAdapter<List<Category>>).apply {
                items = listOf(
                    Category.ProductCategory(R.drawable.phone, R.string.phones),
                    Category.ProductCategory(R.drawable.headphones, R.string.headphones),
                    Category.ProductCategory(R.drawable.game, R.string.games),
                    Category.ProductCategory(R.drawable.car, R.string.cars),
                    Category.ProductCategory(R.drawable.furniture, R.string.furniture),
                    Category.ProductCategory(R.drawable.kids, R.string.kids),
                )
                notifyItemRangeInserted(0, 6)
            }
        }
    }

fun latestProductsAdapterDelegate() =
    adapterDelegateViewBinding<Product.LatestSection, Product, LayoutLatestBinding>(
        { layoutInflater, parent ->
            LayoutLatestBinding.inflate(layoutInflater, parent, false).apply {
                rvLatest.adapter = ListDelegationAdapter(latestProductAdapterDelegate())
            }
        }
    ) {
        bind {
            (binding.rvLatest.adapter as ListDelegationAdapter<List<ProductView.ProductWithoutSaleView>>).apply {
                items = item.latestProducts
                notifyDataSetChanged()
            }
        }
    }

fun flashSaleProductsAdapterDelegate() =
    adapterDelegateViewBinding<Product.FlashSaleSection, Product, LayoutFlashSaleBinding>(
        { layoutInflater, parent ->
            LayoutFlashSaleBinding.inflate(layoutInflater, parent, false).apply {
                rvFlashSale.adapter = ListDelegationAdapter(flashSaleProductAdapterDelegate())
            }
        }
    ) {
        bind {
            (binding.rvFlashSale.adapter as ListDelegationAdapter<List<ProductView.ProductOnSaleView>>).apply {
                items = item.flashSaleProducts
                notifyDataSetChanged()
            }
        }
    }

fun brandsAdapterDelegate() =
    adapterDelegateViewBinding<Product.BrandSection, Product, LayoutBrandsBinding>(
        { layoutInflater, parent ->
            LayoutBrandsBinding.inflate(layoutInflater, parent, false).apply {
                rvBrands.adapter = ListDelegationAdapter(brandAdapterDelegate())
            }
        }
    ) {
        bind {
            (binding.rvBrands.adapter as ListDelegationAdapter<List<Brand.ProductBrand>>).apply {
                items = listOf(
                    Brand.ProductBrand("https://www.macmillandictionary.com/us/dictionary/american/gray_1"),
                    Brand.ProductBrand("https://www.macmillandictionary.com/us/dictionary/american/gray_1"),
                    Brand.ProductBrand("https://www.macmillandictionary.com/us/dictionary/american/gray_1")
                )
                notifyDataSetChanged()
            }
        }
    }

private fun categoryAdapterDelegate() =
    adapterDelegateViewBinding<Category.ProductCategory, Category, ViewCategoryBinding>(
        { layoutInflater, parent ->
            ViewCategoryBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.ivCategoryIcon.setImageResource(item.icon)
            binding.tvCategoryName.setText(item.title)
        }
    }

private fun latestProductAdapterDelegate() =
    adapterDelegateViewBinding<ProductView.ProductWithoutSaleView, ProductView, ViewLatestBinding>(
        { layoutInflater, parent ->
            ViewLatestBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.tvLatestName.text = item.name
            binding.tvLatestCategory.text = item.category
            binding.tvLatestPrice.text = "\$ ${item.price}"
            Glide
                .with(binding.root.context)
                .load(item.imageUrl)
                .transform(
                    CenterCrop(),
                    RoundedCorners(9)
                )
                .placeholder(R.drawable.placeholder_latest)
                .into(binding.ivLatestIcon)
        }
    }

private fun flashSaleProductAdapterDelegate() =
    adapterDelegateViewBinding<ProductView.ProductOnSaleView, ProductView, ViewFlashSaleBinding>(
        { layoutInflater, parent ->
            ViewFlashSaleBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            binding.tvFlashSaleProductName.text = item.name
            binding.tvFlashSaleCategory.text = item.category
            binding.tvFlashSalePrice.text = "\$ ${item.price}"
            binding.tvSale.text = "${item.discount}% off"
            Glide
                .with(binding.root.context)
                .load(item.imageUrl)
                .transform(
                    CenterCrop(),
                    RoundedCorners(11)
                )
                .placeholder(R.drawable.placeholder_flash_sale)
                .into(binding.ivFlashSaleIcon)
        }
    }

private fun brandAdapterDelegate() =
    adapterDelegateViewBinding<Brand.ProductBrand, Brand, ViewBrandBinding>(
        { layoutInflater, parent ->
            ViewBrandBinding.inflate(layoutInflater, parent, false)
        }
    ) {
        bind {
            Glide
                .with(binding.root.context)
                .load(item.imageUrl)
                .transform(
                    CenterCrop(),
                    RoundedCorners(9)
                )
                .placeholder(R.drawable.placeholder_latest)
                .into(binding.ivBrandIcon)
        }
    }

sealed class Category {
    data class ProductCategory(@DrawableRes val icon: Int, @StringRes val title: Int) : Category()
}