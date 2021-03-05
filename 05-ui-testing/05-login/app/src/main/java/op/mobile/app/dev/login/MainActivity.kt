package op.mobile.app.dev.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val EXTRA_EMAIL_ADDRESS = "op.mobile.app.dev.login.EMAIL_ADDRESS"
const val EXTRA_PASSWORD = "op.mobile.app.dev.login.PASSWORD"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmailAddress: EditText = findViewById(R.id.et_email_address)
        val etPassword: EditText = findViewById(R.id.et_password)

        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_EMAIL_ADDRESS, etEmailAddress.text.toString())
                putExtra(EXTRA_PASSWORD, etPassword.text.toString())
            }
            startActivity(intent)
        }
    }
}