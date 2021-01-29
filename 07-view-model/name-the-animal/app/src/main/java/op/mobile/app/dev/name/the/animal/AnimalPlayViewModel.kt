package op.mobile.app.dev.name.the.animal

import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    private lateinit var animalSoundList: MutableList<String>
    var animalSound: String
    var score: Int
    var isEnd: Boolean

    init {
        animalSound = ""
        score = 0
        isEnd = false
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
            animalSound = animalSoundList.removeAt(0)
        }
    }

    fun onSkip() {
        if (score != 0) {
            score--
        }
        nextAnimalSound()
    }

    fun onCorrect() {
        score++
        nextAnimalSound()
    }

    private fun onEnd() {
        isEnd = true
    }

    fun onEndComplete() {
        isEnd = false
    }
}