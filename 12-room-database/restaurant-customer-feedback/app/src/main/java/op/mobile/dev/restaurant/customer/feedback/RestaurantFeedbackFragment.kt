package op.mobile.dev.restaurant.customer.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class RestaurantFeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentRestaurantFeedbackBinding>(
            inflater, R.layout.fragment_restaurant_feedback, container, false
        )

        val dataSource = RestaurantDB.getInstance(requireNotNull(activity).application).restaurantDAO

        val viewModelFactory = RestaurantFeedbackViewModelFactory(RestaurantFeedbackFragmentArgs.fromBundle(requireArguments()).sleepNightKey, dataSource)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantFeedbackViewModel::class.java)

        binding.sleepQualityViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToRestaurantTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    RestaurantFeedbackFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewModel.onNavigateComplete()
            }
        })

        return binding.root
    }
}