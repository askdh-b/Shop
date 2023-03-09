package rustam.urazov.core.validation

abstract class Rule<T> {

    abstract fun validate(value: T): ValidationResult

}