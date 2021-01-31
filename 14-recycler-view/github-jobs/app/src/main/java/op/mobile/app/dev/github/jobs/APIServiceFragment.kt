package op.mobile.app.dev.github.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.github.jobs.databinding.FragmentApiServiceBinding

class APIServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentApiServiceBinding>(
            inflater, R.layout.fragment_api_service, container, false
        )

        val viewModel = ViewModelProvider(this).get(APIServiceViewModel::class.java)

        binding.lifecycleOwner = this

        binding.apiServiceViewModel = viewModel

        binding.recyclerViewProperties.adapter = APIServiceAdapter()

        return binding.root
    }
}