package op.mobile.app.dev.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Get the Intent that started SecondActivity & retrieve the value
        val emailAddress = intent.getStringExtra(EXTRA_EMAIL_ADDRESS)

        // Set the retrieved value to the TextView
        val tvOutput: TextView = findViewById(R.id.tv_output)
        tvOutput.text = emailAddress
    }
}