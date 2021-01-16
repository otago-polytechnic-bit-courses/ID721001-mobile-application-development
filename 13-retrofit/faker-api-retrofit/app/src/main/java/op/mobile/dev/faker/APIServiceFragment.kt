package op.mobile.dev.faker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.dev.faker.databinding.FragmentApiServiceBinding

class APIServiceFragment : Fragment() {

    private lateinit var viewModel: APIServiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentApiServiceBinding>(
            inflater, R.layout.fragment_api_service, container, false
        )

        viewModel = ViewModelProvider(this).get(APIServiceViewModel::class.java)

        binding.lifecycleOwner = this

        binding.apiServiceViewModel = viewModel

        return binding.root
    }
}