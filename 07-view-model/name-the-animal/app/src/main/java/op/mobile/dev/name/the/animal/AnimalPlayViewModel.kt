package op.mobile.dev.name.the.animal

import androidx.lifecycle.ViewModel

class AnimalPlayViewModel : ViewModel() {

    var animalSoundList: MutableList<String> =
        mutableListOf("Baa", "Bow-Wow", "Buzz", "Moo", "Neigh", "Oink Oink", "Ribbit", "Quack")
    var isEmpty = false
    var animalSound = ""
    var score = 0

    init {
//        animalSoundList.shuffle()
        nextAnimalSound()
    }

    fun onSkip() {
        if (score != 0) {
            score--
            nextAnimalSound()
        }
    }

    fun onCorrect() {
        score++
        nextAnimalSound()
    }

    private fun nextAnimalSound() {
        if (animalSoundList.isNotEmpty()) {
            animalSound = animalSoundList.removeAt(0)
        }
    }
}