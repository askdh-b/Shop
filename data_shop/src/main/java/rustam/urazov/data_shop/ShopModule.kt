package rustam.urazov.data_shop

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rustam.urazov.data_shop.repository.ProductRepository
import rustam.urazov.data_shop.repository.ProductRepositoryImpl
import rustam.urazov.data_shop_network.ShopApi
import rustam.urazov.data_shop_network.ShopService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShopModule {

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository =
        productRepository

    @Provides
    @Singleton
    fun provideShopApi(shopService: ShopService): ShopApi = shopService
}