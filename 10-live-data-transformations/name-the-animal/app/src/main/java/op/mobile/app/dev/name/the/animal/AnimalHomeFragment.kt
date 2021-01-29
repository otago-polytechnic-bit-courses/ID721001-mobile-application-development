package op.mobile.app.dev.name.the.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.name.the.animal.databinding.FragmentAnimalHomeBinding

class AnimalHomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentAnimalHomeBinding>(
                inflater, R.layout.fragment_animal_home, container, false
        )

        binding.btnPlay.setOnClickListener {
            findNavController().navigate(AnimalHomeFragmentDirections.actionAnimalHomeFragmentToAnimalPlayFragment())
        }

        return binding.root
    }
}