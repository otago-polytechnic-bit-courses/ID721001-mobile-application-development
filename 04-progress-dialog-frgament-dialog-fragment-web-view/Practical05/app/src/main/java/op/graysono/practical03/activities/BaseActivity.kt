package op.graysono.practical03.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import op.graysono.practical03.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    internal fun displayToolbar(isHomeEnabled: Boolean) {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeEnabled)
    }
}