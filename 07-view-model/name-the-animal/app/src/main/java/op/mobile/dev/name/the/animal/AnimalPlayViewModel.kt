package op.mobile.dev.name.the.animal

import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    var animalSoundList: MutableList<String> =
        mutableListOf("Baa", "Bow-Wow", "Buzz", "Moo", "Neigh", "Oink Oink", "Ribbit", "Quack")
    var animalSound: String
    var score: Int
    var isEnd: Boolean

    init {
        animalSoundList.shuffle()
        animalSound = ""
        score = 0
        isEnd = false
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

    private fun nextAnimalSound() {
        if (animalSoundList.isNotEmpty()) {
            animalSound = animalSoundList.removeAt(0)
        }

        if (animalSoundList.isEmpty()) {
            isEnd = true
        }
    }
}