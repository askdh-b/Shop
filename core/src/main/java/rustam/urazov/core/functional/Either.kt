package rustam.urazov.core.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isLeft get() = this is Left
    val isRight get() = this is Right

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)
}

fun <T, L, R> Either<L, R>.fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
    when (this) {
        is Either.Left -> fnL(a)
        is Either.Right -> fnR(b)
    }

fun <A, B, C> ((A) -> B).c(fn: (B) -> C): (A) -> C = {
    fn(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }

fun <T, L, R> Either<L, R>.map(fn : (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))

fun <L, R> Either<L, R>.getOrElse(value: R) =
    when (this) {
        is Either.Left -> value
        is Either.Right -> b
    }

fun <L, R> Either<L, R>.onFailure(fn: (failure: L) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Left) fn(a) }

fun <L, R> Either<L, R>.onSuccess(fn: (success: R) -> Unit): Either<L, R> =
    this.apply { if (this is Either.Right) fn(b) }