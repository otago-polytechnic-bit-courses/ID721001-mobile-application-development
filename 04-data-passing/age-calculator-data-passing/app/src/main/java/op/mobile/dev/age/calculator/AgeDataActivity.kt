package op.mobile.dev.age.calculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AgeDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_data)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val txtViewSelectedDate: TextView = findViewById(R.id.txt_view_selected_date)
        val txtViewMinutesDifference: TextView = findViewById(R.id.txt_view_minutes_difference)

        txtViewSelectedDate.text = intent.getStringExtra("selected_date")
        txtViewMinutesDifference.text = intent.getStringExtra("difference_in_minutes")
    }
}