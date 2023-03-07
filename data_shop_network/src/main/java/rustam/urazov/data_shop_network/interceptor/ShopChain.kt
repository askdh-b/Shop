package rustam.urazov.data_shop_network.interceptor

import okhttp3.*
import java.util.concurrent.TimeUnit

class ShopChain(private val delegate: Interceptor.Chain) : Interceptor.Chain {

    override fun call(): Call = delegate.call()

    override fun connectTimeoutMillis(): Int = delegate.connectTimeoutMillis()

    override fun connection(): Connection? = delegate.connection()

    override fun proceed(request: Request): Response = delegate.proceed(request)

    override fun readTimeoutMillis(): Int = delegate.readTimeoutMillis()

    override fun request(): Request = Request.Builder()
        .url(delegate.request().url)
        .build()

    override fun withConnectTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = delegate.withConnectTimeout(timeout, unit)

    override fun withReadTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = delegate.withReadTimeout(timeout, unit)

    override fun withWriteTimeout(timeout: Int, unit: TimeUnit): Interceptor.Chain = delegate.withWriteTimeout(timeout, unit)

    override fun writeTimeoutMillis(): Int = delegate.writeTimeoutMillis()
}