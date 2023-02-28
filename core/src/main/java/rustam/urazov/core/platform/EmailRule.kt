package rustam.urazov.core.platform

import rustam.urazov.core.validation.Rule
import rustam.urazov.core.validation.ValidationResult

class EmailRule : Rule<String>() {

    override fun validate(value: String): ValidationResult {
        val regex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
        return when (regex.containsMatchIn(value)) {
            true -> ValidationResult.Valid
            false -> ValidationResult.Invalid("Invalid email")
        }
    }

}