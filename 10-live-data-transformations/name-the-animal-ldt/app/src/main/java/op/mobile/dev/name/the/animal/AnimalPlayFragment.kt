package op.mobile.dev.name.the.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import op.mobile.dev.name.the.animal.databinding.FragmentAnimalPlayBinding

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

        binding.animalPlayViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isEnd.observe(viewLifecycleOwner, { _isEnd ->
            if (_isEnd) onEnd()
        })

        return binding.root
    }

    private fun onEnd() {
        val action = AnimalPlayFragmentDirections.actionAnimalPlayFragmentToAnimalResultFragment()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndComplete()
    }
}