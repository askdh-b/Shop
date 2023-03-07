package rustam.urazov.data_shop_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ShopInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val shopChain = ShopChain(chain)
        val request = shopChain.request()
        return shopChain.proceed(request)
    }

}