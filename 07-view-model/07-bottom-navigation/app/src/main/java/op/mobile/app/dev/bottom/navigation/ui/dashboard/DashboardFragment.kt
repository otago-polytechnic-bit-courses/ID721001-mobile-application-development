package op.mobile.app.dev.bottom.navigation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import op.mobile.app.dev.bottom.navigation.R

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val tvOutput: TextView = view.findViewById(R.id.tv_count)
        val btnPlusOne: Button = view.findViewById(R.id.btn_plus_one)

        var count = 0
        
        tvOutput.text = count.toString()

        btnPlusOne.setOnClickListener {
            count ++
            tvOutput.text = count.toString()
        }

        return view
    }
}
