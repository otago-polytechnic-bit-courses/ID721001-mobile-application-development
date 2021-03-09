package op.mobile.app.dev.explicit.intent

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnViewAMap: Button = findViewById(R.id.btn_view_a_map)
        btnViewAMap.setOnClickListener {
            try {
                val location: Uri = Uri.parse("geo:-45.865927,170.518522?z=14")
                val intent = Intent(Intent.ACTION_VIEW, location)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this@MainActivity,
                    "Unable to handle this request. Please try again later.",
                    Toast.LENGTH_LONG
                ).show();
            }
        }

        val btnViewAWebPage: Button = findViewById(R.id.btn_view_a_web_page)
        btnViewAWebPage.setOnClickListener {
            try {
                val webPage: Uri = Uri.parse("https://www.youtube.com")
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this@MainActivity,
                    "Unable to handle this request. Please try again later.",
                    Toast.LENGTH_LONG
                ).show();
            }
        }

    }
}