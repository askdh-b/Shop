package rustam.urazov.core.validation

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val message: String) : ValidationResult()

    fun plus(term: ValidationResult): ValidationResult =
        when (this is Invalid) {
            true -> this
            false -> when (term is Invalid) {
                true -> term
                false -> Valid
            }
        }
}