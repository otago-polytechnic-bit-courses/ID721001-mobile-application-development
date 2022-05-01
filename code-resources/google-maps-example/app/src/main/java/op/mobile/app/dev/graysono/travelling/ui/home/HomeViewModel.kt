package op.mobile.app.dev.graysono.travelling.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.graysono.travelling.api.RetrofitInstance
import op.mobile.app.dev.graysono.travelling.api.ServiceStatus
import op.mobile.app.dev.graysono.travelling.model.Country

class HomeViewModel : ViewModel() {
    private val baseUrl =
        "https://gist.github.com/Grayson-Orr/49223bcae755ef9479b3150182dc125e/"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitCountryService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}
