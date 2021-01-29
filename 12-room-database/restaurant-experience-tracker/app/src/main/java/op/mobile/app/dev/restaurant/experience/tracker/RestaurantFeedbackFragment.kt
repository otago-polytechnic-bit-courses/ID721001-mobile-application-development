package op.mobile.app.dev.restaurant.experience.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.restaurant.experience.tracker.databinding.FragmentRestaurantFeedbackBinding

class RestaurantFeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentRestaurantFeedbackBinding>(
            inflater, R.layout.fragment_restaurant_feedback, container, false
        )

        val dataSource =
            RestaurantDB.getInstance(requireNotNull(activity).application).restaurantDAO

        val viewModelFactory = RestaurantFeedbackViewModelFactory(
            RestaurantFeedbackFragmentArgs.fromBundle(requireArguments()).primaryKey, dataSource
        )

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(RestaurantFeedbackViewModel::class.java)

        binding.restaurantFeedbackViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToRestaurantTracker.observe(viewLifecycleOwner, {
            if (it == true) {
                findNavController().navigate(
                    RestaurantFeedbackFragmentDirections.actionRestaurantFeedbackFragmentFragmentToRestaurantTimeTrackerFragment()
                )
                viewModel.onNavigateComplete()
            }
        })

        return binding.root
    }
}