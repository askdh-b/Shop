package rustam.urazov.data_shop

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rustam.urazov.data_shop_network.adapterFactory.ShopCallAdapterFactory
import rustam.urazov.data_shop_network.interceptor.ShopInterceptor
import rustam.urazov.data_shop.repository.ProductRepository
import rustam.urazov.data_shop.repository.ProductRepositoryImpl
import rustam.urazov.data_shop_network.ShopApi
import rustam.urazov.data_shop_network.ShopService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShopDataModule {

    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(createClient())
        .addCallAdapterFactory(ShopCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        okHttpClientBuilder
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ShopInterceptor())

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(productRepository: ProductRepositoryImpl): ProductRepository =
        productRepository

    @Provides
    @Singleton
    fun provideShopApi(shopService: ShopService): ShopApi = shopService
}