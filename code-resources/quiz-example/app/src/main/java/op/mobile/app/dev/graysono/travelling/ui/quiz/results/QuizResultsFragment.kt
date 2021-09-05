package op.mobile.app.dev.graysono.travelling.ui.quiz.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.graysono.travelling.R
import op.mobile.app.dev.graysono.travelling.databinding.FragmentQuizResultsBinding

class QuizResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentQuizResultsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_results,
            container,
            false
        )

        val bundle = QuizResultsFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory =
            QuizResultsViewModelFactory(bundle.score)

        val viewModel: QuizResultsViewModel by viewModels { viewModelFactory }

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizResultsViewModel = viewModel
            return root
        }
    }
}
