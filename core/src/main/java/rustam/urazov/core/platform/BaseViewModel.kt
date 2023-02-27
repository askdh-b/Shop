package rustam.urazov.core.platform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import rustam.urazov.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    private val mutableFailure: MutableStateFlow<Failure> = MutableStateFlow(Failure.NoError)
    val failure: StateFlow<Failure> = mutableFailure.asStateFlow()

    protected fun handleFailure(failure: Failure) {
        mutableFailure.value = failure
    }

}