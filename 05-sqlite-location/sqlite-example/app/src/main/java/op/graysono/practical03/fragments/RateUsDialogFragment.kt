package op.graysono.practical03.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import androidx.fragment.app.DialogFragment
import op.graysono.practical03.R
import op.graysono.practical03.interfaces.IDataReceived

class RateUsDialogFragment(private val listener: IDataReceived) : DialogFragment() {
    private lateinit var rateUsNowBtn: Button
    private lateinit var rateUsNoBtn: Button
    private lateinit var rateUsRatingBar: RatingBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        return inflater.inflate(R.layout.fragment_rate_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rateUsNowBtn = view.findViewById(R.id.rate_us_now_btn)
        rateUsNoBtn = view.findViewById(R.id.rate_us_no_btn)
        rateUsRatingBar = view.findViewById(R.id.rate_us_rating_bar)
        rateUsNowBtn.isEnabled = false
        rateUsRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            rateUsNowBtn.isEnabled = rating > 0.0f
        }
        rateUsNoBtn.setOnClickListener { dismiss() }
        rateUsNowBtn.setOnClickListener {
            listener.onDataReceived("Thank you! Your feedback is very helpful for us.")
            dismiss()
        }
    }
}
