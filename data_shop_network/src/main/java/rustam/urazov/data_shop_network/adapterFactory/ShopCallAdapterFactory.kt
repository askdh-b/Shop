package rustam.urazov.data_shop_network.adapterFactory

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ShopCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<out Any, Call<Either<Failure, *>>>? {

        if (returnType !is ParameterizedType) return null

        val containerType = getParameterUpperBound(0, returnType)
        if (getRawType(containerType) != Either::class.java) return null
        if (containerType !is ParameterizedType) return null

        val errorType = getParameterUpperBound(0, containerType)
        if (getRawType(errorType) != Failure::class.java) return null

        val resultType = getParameterUpperBound(1, containerType)
        return ResultCallAdapter(resultType)

    }

}