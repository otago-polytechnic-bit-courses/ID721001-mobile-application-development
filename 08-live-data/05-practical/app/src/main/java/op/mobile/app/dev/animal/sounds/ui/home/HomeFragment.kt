package op.mobile.app.dev.animal.sounds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.animal.sounds.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val btnPlay: Button = view.findViewById(R.id.btn_play)
        btnPlay.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGameFragment())
        }

        return view
    }
}