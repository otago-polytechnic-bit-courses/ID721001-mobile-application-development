package op.mobile.dev.github.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.dev.github.jobs.APIService.retrofitService

class APIServiceViewModel : ViewModel() {
    private val _status = MutableLiveData<APIServiceStatus>()
    val status: LiveData<APIServiceStatus> 
        get() = _status

    private val _properties = MutableLiveData<List<APIServiceProperty>>()
    val properties: LiveData<List<APIServiceProperty>> 
        get() = _properties

    init {
        getAPIServiceProperties()
    }

    private fun getAPIServiceProperties() {
        viewModelScope.launch {
            _status.value = APIServiceStatus.LOADING
            try {
                _properties.value = retrofitService.getProperties()
                _status.value = APIServiceStatus.COMPLETE
            } catch (e: Exception) {
                _properties.value = ArrayList()
                _status.value = APIServiceStatus.ERROR
            }
        }
    }
}