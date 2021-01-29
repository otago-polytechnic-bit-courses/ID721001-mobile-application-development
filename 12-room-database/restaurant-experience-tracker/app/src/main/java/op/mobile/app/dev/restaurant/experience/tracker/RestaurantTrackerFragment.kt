package op.mobile.app.dev.restaurant.experience.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import op.mobile.app.dev.restaurant.experience.tracker.databinding.FragmentRestaurantTimeTrackerBinding

class RestaurantTimeTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentRestaurantTimeTrackerBinding>(
            inflater, R.layout.fragment_restaurant_time_tracker, container, false
        )

        val application = requireNotNull(activity).application

        val dataSource = RestaurantDB.getInstance(application).restaurantDAO

        val viewModelFactory = RestaurantTimeTrackerViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(RestaurantTimeTrackerViewModel::class.java)

        binding.restaurantTimeTrackerViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.showSnackBar.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Restaurant History Deleted.",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.onShowSnackBarComplete()
            }
        })

        viewModel.navigateToRestaurantFeedback.observe(viewLifecycleOwner, { data ->
            data?.let {
                findNavController().navigate(
                    RestaurantTimeTrackerFragmentDirections.actionRestaurantTimeTrackerFragmentToRestaurantFeedbackFragment(
                        data.restaurantEntryId
                    )
                )
                viewModel.onNavigateToRestaurantFeedbackComplete()
            }
        })

        return binding.root
    }
}