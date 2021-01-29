package op.mobile.app.dev.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.quiz.databinding.FragmentQuizWinBinding

class QuizWinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentQuizWinBinding>(
            inflater, R.layout.fragment_quiz_win, container, false
        )

        binding.btnWinPlayAgain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_quiz_win_to_quiz_play)
        }

        return binding.root
    }
}