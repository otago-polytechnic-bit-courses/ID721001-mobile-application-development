package op.mobile.app.dev.animal.sounds.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import op.mobile.app.dev.animal.sounds.R
import op.mobile.app.dev.animal.sounds.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game, container, false
        )
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isEnd.observe(viewLifecycleOwner, {
            if (it) onEnd()
        })

        return binding.root
    }

    private fun onEnd() {
        val action = GameFragmentDirections.actionGameFragmentToResultsFragment()
        action.score = viewModel.score.value!!
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndComplete()
    }
}