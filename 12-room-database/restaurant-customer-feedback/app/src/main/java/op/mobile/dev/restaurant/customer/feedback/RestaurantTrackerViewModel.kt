package op.mobile.dev.restaurant.customer.feedback

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class RestaurantTimeTrackerViewModel(private val dbDao: RestaurantDAO, application: Application) : AndroidViewModel(application) {

    private var restaurant = MutableLiveData<Restaurant?>()

    private val restaurantData = dbDao.getAllRestaurantData()

    private var _showSnackBar = MutableLiveData<Boolean>()
    val showSnackBar: LiveData<Boolean>
        get() = _showSnackBar

    private val _navigateToRestaurantFeedback = MutableLiveData<Restaurant>()
    val navigateToRestaurantFeedback: LiveData<Restaurant>
        get() = _navigateToRestaurantFeedback

    val isStartBtnVisible = Transformations.map(restaurant) { null == it }
    val isStopBtnVisible = Transformations.map(restaurant) { null != it }
    val isResetBtnVisible = Transformations.map(restaurantData) { it?.isNotEmpty() }

    val restaurantHistoryString = Transformations.map(restaurantData) { data ->
        formatRestaurantData(data, application.resources)
    }

    init {
        viewModelScope.launch {
            restaurant.value = getRestaurantHistoryFromDatabase()
        }
    }

    fun onShowSnackBarComplete() {
        _showSnackBar.value = false
    }

    fun onNavigateToRestaurantFeedbackComplete() {
        _navigateToRestaurantFeedback.value = null
    }

    private suspend fun getRestaurantHistoryFromDatabase(): Restaurant? {
        var data = dbDao.getRestaurantData()
        if (data?.endTimeMilli != data?.startTimeMilli) data = null
        return data
    }

    private suspend fun insert(restaurant: Restaurant) {
        dbDao.insert(restaurant)
    }

    private suspend fun update(restaurant: Restaurant) {
        dbDao.update(restaurant)
    }

    private suspend fun clear() {
        dbDao.clear()
    }

    fun onStartTracking() {
        viewModelScope.launch {
            val newRestaurantData = Restaurant()
            insert(newRestaurantData)
            restaurant.value = getRestaurantHistoryFromDatabase()
        }
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val oldRestaurantData = restaurant.value ?: return@launch
            oldRestaurantData.endTimeMilli = System.currentTimeMillis()
            update(oldRestaurantData)
            _navigateToRestaurantFeedback.value = oldRestaurantData
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            restaurant.value = null
            _showSnackBar.value = true
        }
    }
}