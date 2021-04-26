package op.mobile.app.dev.animal.sounds.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.animal.sounds.R
import op.mobile.app.dev.animal.sounds.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    private lateinit var viewModel: ResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_results, container, false
        )

        val viewModelFactory =
            ResultsViewModelFactory(ResultsFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)

        binding.resultsViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isPlayAgain.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        })

        activity?.onBackPressedDispatcher?.addCallback(this) {}

        return binding.root
    }
}