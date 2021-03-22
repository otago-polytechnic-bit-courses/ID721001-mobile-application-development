package op.mobile.app.dev.bottom.navigation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    init {
        _count.value = 0
    }

    fun plusOne() {
        _count.value = _count.value?.plus(1)
    }

    fun reset() {
        _count.value = 0
    }
}