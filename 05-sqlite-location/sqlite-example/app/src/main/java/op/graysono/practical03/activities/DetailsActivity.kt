package op.graysono.practical03.activities

import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import op.graysono.practical03.R
import op.graysono.practical03.helpers.Album

class DetailsActivity : AppCompatActivity() {

    private lateinit var albumNameText: TextView
    private lateinit var albumImageView: CircleImageView
    private lateinit var albumPlayCountText: TextView
    private lateinit var albumWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val album: Album? = intent.extras?.getParcelable("album")

        albumNameText = findViewById(R.id.album_name_text)
        albumImageView = findViewById(R.id.album_image_view)
        albumPlayCountText = findViewById(R.id.album_play_count_text)
        albumWebView = findViewById(R.id.album_web_view)

        albumNameText.text = getString(R.string.album_name, album?.name)

        Picasso.with(this@DetailsActivity).load(album?.image)
            .error(R.drawable.ic_album_black_24)
            .placeholder(R.drawable.ic_album_black_24)
            .into(albumImageView)

        albumPlayCountText.text = getString(R.string.play_count, album?.playCount.toString())

        albumWebView.settings.javaScriptEnabled = true
        album?.url?.let { albumWebView.loadUrl(it) }
    }
}
