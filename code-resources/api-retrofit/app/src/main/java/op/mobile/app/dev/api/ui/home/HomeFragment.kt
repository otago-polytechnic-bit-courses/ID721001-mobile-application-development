package op.mobile.app.dev.api.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.api.R
import op.mobile.app.dev.api.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.homeViewModel = viewModel

        return binding.root
    }
}