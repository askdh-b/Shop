package rustam.urazov.data_shop_network.adapterFactory

import retrofit2.Call
import retrofit2.CallAdapter
import rustam.urazov.core.exception.Failure
import rustam.urazov.core.functional.Either
import java.lang.reflect.Type

class ResultCallAdapter<R, T>(private val resultType: Type) :
    CallAdapter<R, Call<Either<Failure, T>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<R>): Call<Either<Failure, T>> = ResultCallWrapper(call)

}