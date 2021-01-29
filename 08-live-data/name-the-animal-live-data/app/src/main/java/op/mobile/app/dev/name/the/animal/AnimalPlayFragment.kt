package op.mobile.app.dev.name.the.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import op.mobile.app.dev.name.the.animal.databinding.FragmentAnimalPlayBinding

class AnimalPlayFragment : Fragment() {

    private lateinit var viewModel: AnimalPlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAnimalPlayBinding>(
            inflater, R.layout.fragment_animal_play, container, false
        )

        viewModel = ViewModelProvider(this).get(AnimalPlayViewModel::class.java)

        viewModel.animalSound.observe(viewLifecycleOwner, { _animalSound ->
            binding.txtViewAnimalSound.text =
                activity?.getString(R.string.what_animal, _animalSound)
        })

        viewModel.score.observe(viewLifecycleOwner, { _score ->
            binding.txtViewScore.text =
                activity?.getString(R.string.current_score, _score.toString())
        })

        viewModel.isEnd.observe(viewLifecycleOwner, { _isEnd ->
            if (_isEnd) onEnd()
        })

        binding.btnSkipAnimalSound.setOnClickListener { onSkip() }
        binding.btnCorrectAnimalSound.setOnClickListener { onCorrect() }
        binding.btnEndGame.setOnClickListener { onEnd() }

        return binding.root
    }

    private fun onSkip() {
        viewModel.onSkip()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
    }

    private fun onEnd() {
        val action = AnimalPlayFragmentDirections.actionAnimalPlayFragmentToAnimalResultFragment()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndComplete()
    }
}