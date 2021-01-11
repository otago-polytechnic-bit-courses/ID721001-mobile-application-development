package op.mobile.dev.name.the.animal

import android.util.Log
import androidx.lifecycle.ViewModel

class AnimalResultViewModel(finalScore: Int) : ViewModel() {
    var score = finalScore

    init {
        Log.i("AnimalResultViewModel", "Score: $score")
    }
}