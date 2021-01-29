package op.mobile.app.dev.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.quiz.databinding.FragmentQuizHomeBinding

class QuizHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentQuizHomeBinding>(
            inflater, R.layout.fragment_quiz_home, container, false
        )

        binding.btnPlay.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_quiz_home_to_quiz_play)
        }

        return binding.root
    }
}