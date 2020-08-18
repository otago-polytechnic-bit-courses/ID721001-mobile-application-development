package graysono.com.cp16notifications.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recording_studio.*

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    internal fun displayToolbar(isHomeEnabled: Boolean, isTitleEnabled: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeEnabled)
        supportActionBar?.setDisplayShowTitleEnabled(isTitleEnabled)
    }
}