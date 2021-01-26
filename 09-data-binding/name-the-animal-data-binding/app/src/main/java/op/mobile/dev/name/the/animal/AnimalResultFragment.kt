package op.mobile.dev.name.the.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.dev.name.the.animal.databinding.FragmentAnimalResultBinding

class AnimalResultFragment : Fragment() {

    private lateinit var viewModel: AnimalResultViewModel
    private lateinit var viewModelFactory: AnimalResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAnimalResultBinding>(
            inflater, R.layout.fragment_animal_result, container, false
        )

        viewModelFactory =
            AnimalResultViewModelFactory(AnimalResultFragmentArgs.fromBundle(requireArguments()).score)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AnimalResultViewModel::class.java)

        binding.animalResultViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isPlayAgain.observe(viewLifecycleOwner, Observer { _isPlayAgain ->
            if (_isPlayAgain) {
                findNavController().navigate(AnimalResultFragmentDirections.actionAnimalResultFragmentToAnimalPlayFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}