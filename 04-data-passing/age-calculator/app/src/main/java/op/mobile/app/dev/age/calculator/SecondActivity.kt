package op.mobile.app.dev.age.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tVSelectedDate: TextView = findViewById(R.id.tv_selected_date)
        val tVMinutesDifference: TextView = findViewById(R.id.tv_minutes_difference)

        tVSelectedDate.text = intent.getStringExtra(EXTRA_SELECTED_DATE)
        tVMinutesDifference.text = intent.getStringExtra(EXTRA_MINUTES_DIFFERENCE)
    }
}