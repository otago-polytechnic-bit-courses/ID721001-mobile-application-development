package op.mobile.app.dev.bottom.navigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    init {
        reset()
    }

    fun plusOne() {
        _count.value = _count.value?.plus(1) // count++ does not work
    }

    fun reset() {
        _count.value = 0
    }
}