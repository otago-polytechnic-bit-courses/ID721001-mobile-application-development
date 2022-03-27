package op.mobile.app.dev.graysono.travelling.ui.quiz.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class QuizResultsViewModelFactory(
    private val score: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizResultsViewModel::class.java))
            return QuizResultsViewModel(score) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
