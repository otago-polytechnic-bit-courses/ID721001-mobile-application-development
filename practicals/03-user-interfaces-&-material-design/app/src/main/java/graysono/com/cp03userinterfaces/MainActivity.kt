package graysono.com.cp03userinterfaces

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Alternative syntax for finding a view by its id - this is recommended by Google
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fabYoutube = findViewById<View>(R.id.fabYoutube)
        fabYoutube.setOnClickListener(YoutubeButtonOnClickListener())

        val rdGroup = findViewById<RadioGroup>(R.id.rdGroup)
        rdGroup.setOnCheckedChangeListener(RadioButtonOnCheckedChangeListener())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this@MainActivity, "Settings", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    inner class YoutubeButtonOnClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            // Add appropriate implicit intent here
        }
    }

    inner class RadioButtonOnCheckedChangeListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(rg: RadioGroup?, id: Int) {
            // Add custom toast code here
        }
    }
}
