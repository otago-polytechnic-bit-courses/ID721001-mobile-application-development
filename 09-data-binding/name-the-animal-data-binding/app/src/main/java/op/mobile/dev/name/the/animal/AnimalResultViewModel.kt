package op.mobile.dev.name.the.animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalResultViewModel(finalScore: Int) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _isPlayAgain = MutableLiveData<Boolean>()
    val isPlayAgain: LiveData<Boolean>
        get() = _isPlayAgain

    init {
        _score.value = finalScore
    }

    fun onPlayAgain() {
        _isPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _isPlayAgain.value = false
    }
}