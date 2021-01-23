package op.mobile.dev.name.the.animal

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    private lateinit var animalSoundList: MutableList<String>

    private val timer: CountDownTimer

    private val _animalSound = MutableLiveData<String>()
    val animalSound: LiveData<String>
        get() = _animalSound

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _isEnd = MutableLiveData<Boolean>()
    val isEnd: LiveData<Boolean>
        get() = _isEnd

    private val _countdownTime = MutableLiveData<Long>()
    private val countdownTime: LiveData<Long>
        get() = _countdownTime

    val countdownTimeString = Transformations.map(countdownTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        _animalSound.value = ""
        _score.value = 0
        resetAnimalSounds()
        nextAnimalSound()

        timer = object : CountDownTimer(ONE_MINUTE, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _countdownTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _countdownTime.value = END
                onEnd()
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun resetAnimalSounds() {
        animalSoundList =
            mutableListOf("Baa", "Bow-Wow", "Buzz", "Moo", "Neigh", "Oink Oink", "Ribbit", "Quack")
        animalSoundList.shuffle()
    }

    private fun nextAnimalSound() {
        if (animalSoundList.isEmpty()) {
            resetAnimalSounds()
        } else {
            _animalSound.value = animalSoundList.removeAt(0)
        }
    }

    fun onSkip() {
        if (_score.value != 0) {
            _score.value = (_score.value)?.minus(1)
        }
        nextAnimalSound()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextAnimalSound()
    }

    fun onEnd() {
        _isEnd.value = true
    }

    fun onEndComplete() {
        _isEnd.value = false
    }

    companion object {
        private const val END = 0L
        private const val ONE_SECOND = 1000L
        private const val ONE_MINUTE = 60000L
    }
}