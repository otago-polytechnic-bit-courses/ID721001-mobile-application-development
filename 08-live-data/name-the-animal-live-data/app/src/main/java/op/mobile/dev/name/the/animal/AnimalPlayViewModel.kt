package op.mobile.dev.name.the.animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    private lateinit var animalSoundList: MutableList<String>

    private val _animalSound = MutableLiveData<String>()
    val animalSound: LiveData<String>
        get() = _animalSound

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _isEnd = MutableLiveData<Boolean>()
    val isEnd: LiveData<Boolean>
        get() = _isEnd

    init {
        _animalSound.value = ""
        _score.value = 0
        resetAnimalSounds()
        nextAnimalSound()
    }

    private fun resetAnimalSounds() {
        animalSoundList =
            mutableListOf("Baa", "Woof", "Buzz", "Moo", "Neigh", "Oink", "Ribbit", "Quack")
        animalSoundList.shuffle()
    }

    private fun nextAnimalSound() {
        if (animalSoundList.isEmpty()) {
            onEnd()
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

    private fun onEnd() {
        _isEnd.value = true
    }

    fun onEndComplete() {
        _isEnd.value = false
    }
}