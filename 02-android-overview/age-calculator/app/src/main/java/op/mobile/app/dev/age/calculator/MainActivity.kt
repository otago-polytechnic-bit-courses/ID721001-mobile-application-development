package op.mobile.app.dev.age.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var txtViewSelectedDate: TextView
    private lateinit var txtViewMinutesDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        txtViewSelectedDate = findViewById(R.id.txt_view_selected_date)
        txtViewMinutesDifference = findViewById(R.id.txt_view_minutes_difference)

        txtViewSelectedDate.text = getString(R.string.selected_date_placeholder_text)
        txtViewMinutesDifference.text = getString(R.string.minutes_difference_placeholder_text)
    }
}