package op.mobile.dev.restaurant.customer.feedback

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RestaurantFeedback : Fragment() {

    companion object {
        fun newInstance() = RestaurantFeedback()
    }

    private lateinit var viewModel: RestaurantFeedbackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_feedback_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestaurantFeedbackViewModel::class.java)
        // TODO: Use the ViewModel
    }

}