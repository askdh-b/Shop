package rustam.urazov.core.exception

sealed class Success {
    object Wait : Success()
    object True : Success()
}