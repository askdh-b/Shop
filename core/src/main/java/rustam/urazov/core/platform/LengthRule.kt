package rustam.urazov.core.platform

import rustam.urazov.core.validation.Rule
import rustam.urazov.core.validation.ValidationResult

class LengthRule(
    private val minLength: Int,
    private val maxLength: Int
) : Rule<String>() {

    override fun validate(value: String): ValidationResult =
        when (value.length >= minLength) {
            true -> when (value.length <= maxLength) {
                true -> ValidationResult.Valid
                false -> ValidationResult.Invalid("Max length is $maxLength")
            }
            false -> ValidationResult.Invalid("Min length is $minLength")
        }

}