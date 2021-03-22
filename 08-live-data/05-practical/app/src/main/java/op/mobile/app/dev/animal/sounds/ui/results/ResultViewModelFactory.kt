package op.mobile.app.dev.animal.sounds.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ResultsViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultsViewModel::class.java))
            return ResultsViewModel(finalScore) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}