package op.mobile.dev.name.the.animal

import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    var animalSoundList: MutableList<String> =
        mutableListOf("Baa", "Bow-Wow", "Buzz", "Moo", "Neigh", "Oink Oink", "Ribbit", "Quack")
    var animalSound: String
    var score: Int
    var isEnd: Boolean

    init {
        animalSound = ""
        score = 0
        isEnd = false
        animalSoundList.shuffle()
        nextAnimalSound()
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

    private fun nextAnimalSound() {
        if (animalSoundList.isEmpty()) {
            onEnd()
        } else {
            animalSound = animalSoundList.removeAt(0)
        }
    }
}