package op.mobile.dev.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.dev.quiz.databinding.FragmentQuizLoseBinding

class QuizLoseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentQuizLoseBinding>(
            inflater, R.layout.fragment_quiz_lose, container, false
        )

        binding.btnLosePlayAgain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_quiz_lose_to_quiz_play)
        }

        return binding.root
    }
}