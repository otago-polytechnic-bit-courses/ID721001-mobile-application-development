package op.mobile.app.dev.bottom.navigation.ui.dashboard

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    var count: Int = 0

    fun plusOne() {
        count++
    }
}