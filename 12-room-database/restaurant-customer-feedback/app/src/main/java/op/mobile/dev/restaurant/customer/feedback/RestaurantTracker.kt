package op.mobile.dev.restaurant.customer.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RestaurantTracker : Fragment() {

    companion object {
        fun newInstance() = RestaurantTracker()
    }

    private lateinit var viewModel: RestaurantTrackerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_tracker_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestaurantTrackerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}