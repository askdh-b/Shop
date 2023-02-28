package rustam.urazov.core.validation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.extension.empty

class Validate<T>(
    initialValue: T,
    private val rules: List<Rule<T>>
) {

    private val mutableState: MutableStateFlow<T> = MutableStateFlow(initialValue)
    val state: StateFlow<T> = mutableState.asStateFlow()
    private val mutableResult: MutableStateFlow<ValidationResult> = MutableStateFlow(ValidationResult.Invalid("Required field"))
    val result: StateFlow<ValidationResult> = mutableResult.asStateFlow()

    fun update(value: T) {
        mutableState.value = value
        mutableResult.value = validate(rules)
    }

    private fun validate(rules: List<Rule<T>>): ValidationResult =
        when (rules.size == 1) {
            true -> rules[0].validate(mutableState.value)
            false -> rules[0].validate(mutableState.value).plus(validate(rules.subList(1, rules.size)))
        }

    class Builder<F : Any> {

        private val rules = mutableListOf<Rule<F>>()
        private var value: F? = null

        fun addValue(value: F): Builder<F> {
            this.value = value
            return this
        }

        fun addRule(rule: Rule<F>): Builder<F> {
            rules.add(rule)
            return this
        }

        fun build(): Validate<F> {
            if (value != null && rules.isNotEmpty()) {
                return Validate(value ?: throw NullPointerException(), rules)
            } else {
                throw NullPointerException()
            }
        }
    }
}