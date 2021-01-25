package op.mobile.dev.restaurant.customer.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class RestaurantTimeTrackerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentRestaurantTimeTrackerBinding>(
            inflater, R.layout.fragment_restaurant_time_tracker, container, false
        )

        val application = requireNotNull(activity).application

        val dataSource = RestaurantDB.getInstance(application).restaurantDAO

        val viewModelFactory = RestaurantTimeTrackerViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(RestaurantTimeTrackerViewModel::class.java)

        binding.sleepTrackerViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.showSnackBar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Restaurant Data Deleted.",
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.onShowSnackBarComplete()
            }
        })

        viewModel.navigateToRestaurantFeedback.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                findNavController().navigate(
                    RestaurantTimeTrackerFragmentDirections.actionSleepTrackerFragmentToSleepQualityFragment(night.restaurantEntryId))
                viewModel.onNavigateToRestaurantFeedbackComplete()
            }
        })

        return binding.root
    }
}