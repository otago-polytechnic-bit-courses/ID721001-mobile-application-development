package op.mobile.app.dev.bottom.navigation.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var count: Int = 0

    fun plusOne() {
        count++ // Increment count by 1
    }

    fun reset() {
        count = 0 // Reset count to 0
    }
}