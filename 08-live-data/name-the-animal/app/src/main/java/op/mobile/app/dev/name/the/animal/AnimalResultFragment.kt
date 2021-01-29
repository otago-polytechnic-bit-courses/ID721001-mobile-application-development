package op.mobile.app.dev.name.the.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.name.the.animal.databinding.FragmentAnimalResultBinding

class AnimalResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAnimalResultBinding>(
            inflater, R.layout.fragment_animal_result, container, false
        )

        val viewModelFactory =
            AnimalResultViewModelFactory(AnimalResultFragmentArgs.fromBundle(requireArguments()).score)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(AnimalResultViewModel::class.java)

        viewModel.score.observe(viewLifecycleOwner, { _score ->
            binding.txtViewScore.text = activity?.getString(R.string.you_scored, _score.toString())
        })

        binding.btnPlayAgain.setOnClickListener { viewModel.onPlayAgain() }

        viewModel.isPlayAgain.observe(viewLifecycleOwner, { _isPlayAgain ->
            if (_isPlayAgain) {
                findNavController().navigate(AnimalResultFragmentDirections.actionAnimalResultFragmentToAnimalPlayFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}