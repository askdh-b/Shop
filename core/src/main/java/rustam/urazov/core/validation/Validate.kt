package rustam.urazov.core.validation

import java.util.Stack

class Validate<T>(
    private val value: T,
    private val rules: Stack<Rule<T>>
) {

    fun validate(): ValidationResult = validate(rules)

    private fun validate(rules: Stack<Rule<T>>): ValidationResult =
        when (rules.size == 1) {
            true -> rules.pop().validate(value)
            false -> rules.pop().validate(value).plus(validate(rules))
        }

    class Builder<F : Any> {

        private val rules = Stack<Rule<F>>()
        private var value: F? = null

        fun addValue(value: F): Builder<F> {
            this.value = value
            return this
        }

        fun addRule(rule: Rule<F>): Builder<F> {
            rules.push(rule)
            return this
        }

        fun build(): Validate<F> {
            if (value != null && !rules.empty()) {
                return Validate(value ?: throw NullPointerException(), rules)
            } else {
                throw NullPointerException()
            }

        }

    }

}