package graysono.com.cp03userinterfaces

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        bnv.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener())
        bnv.menu.getItem(1).isChecked = true
    }

    inner class OnNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this@DashboardActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_dashboard -> true
                else -> onNavigationItemSelected(item)
            }
        }
    }
}

