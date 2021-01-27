package op.mobile.dev.restaurant.experience.tracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantFeedbackViewModel(
    private val primaryKey: Long = 0L,
    private val db: RestaurantDAO
) : ViewModel() {

    private val _navigateToRestaurantTracker = MutableLiveData<Boolean?>()
    val navigateToRestaurantTracker: LiveData<Boolean?>
        get() = _navigateToRestaurantTracker

    fun onNavigateComplete() {
        _navigateToRestaurantTracker.value = null
    }

    fun onSetRestaurantRating(rating: Int) {
        viewModelScope.launch {
            val restaurant = db.get(primaryKey) ?: return@launch
            restaurant.restaurantRating = rating
            db.update(restaurant)
            _navigateToRestaurantTracker.value = true
        }
    }
}