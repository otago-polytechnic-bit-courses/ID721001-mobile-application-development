package op.mobile.app.dev.animal.sounds.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import op.mobile.app.dev.animal.sounds.R

class GameFragment : Fragment() {
    private lateinit var tvSound: TextView
    private lateinit var tvScore: TextView
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)

        tvSound = view.findViewById(R.id.tv_sound)
        tvScore = view.findViewById(R.id.tv_score)
        val btnSkip: TextView = view.findViewById(R.id.btn_skip)
        val btnCorrect: Button = view.findViewById(R.id.btn_correct)
        val btnEnd: Button = view.findViewById(R.id.btn_end)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        btnSkip.setOnClickListener { onSkip() }
        btnCorrect.setOnClickListener { onCorrect() }
        btnEnd.setOnClickListener { onEnd() }

        updateText()

        return view
    }

    private fun updateText() {
        tvSound.text = activity?.getString(R.string.what_animal_makes_the_sound, viewModel.animalSound)
        tvScore.text = activity?.getString(R.string.current_score, viewModel.score.toString())
    }

    private fun onSkip() {
        viewModel.onSkip()
        updateText()
        if (viewModel.isEnd)
            onEnd()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        updateText()
        if (viewModel.isEnd)
            onEnd()
    }

    private fun onEnd() {
        val action = GameFragmentDirections.actionGameFragmentToResultsFragment()
        action.score = viewModel.score
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndComplete()
    }
}