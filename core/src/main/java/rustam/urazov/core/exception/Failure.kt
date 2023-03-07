package rustam.urazov.core.exception

sealed class Failure {
    object NoError : Failure()
    object ConnectionError : Failure()
    data class ServerError(val message: String) : Failure()
    data class MemoryError(val message: String) : Failure()
    object UnexpectedError : Failure()
}