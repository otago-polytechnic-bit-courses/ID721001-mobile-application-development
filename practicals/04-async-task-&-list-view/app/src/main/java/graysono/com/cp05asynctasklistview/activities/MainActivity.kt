package graysono.com.cp05asynctasklistview.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import graysono.com.cp05asynctasklistview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var feedAsyncTask: FeedAsyncTask
    private var feedCachedUrl = ""
    private val feedUrlKey = "feedUrl"
    private val feedTitleKey = "feedTitle"
    private var feedUrl: String = ""
    private var feedTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        feedUrl = getString(R.string.rss_feed_url_free)
        feedTitle = getString(R.string.top_free_apps)


        if (savedInstanceState != null) {
            feedUrl = savedInstanceState.getString(feedUrlKey)
            feedTitle = savedInstanceState.getString(feedTitleKey)
        }

        downloadUrl(feedUrl, feedTitle)
        bnv.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(feedUrlKey, feedUrl)
        outState.putString(feedTitleKey, feedTitle)
    }

    override fun onDestroy() {
        super.onDestroy()
        feedAsyncTask.cancel(true)
    }

    private fun downloadUrl(feedUrl: String, title: String) {
        if (feedUrl != feedCachedUrl) {
            txvApp.text = title
            feedAsyncTask = FeedAsyncTask(this@MainActivity, lsvXML)
            feedAsyncTask.execute(feedUrl)
            feedCachedUrl = feedUrl
        }
    }

    inner class OnNavigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_free -> {
                    feedUrl = getString(R.string.rss_feed_url_free)
                    feedTitle = getString(R.string.top_free_apps)
                }
                R.id.navigation_paid -> {
                    feedUrl = getString(R.string.rss_feed_url_paid)
                    feedTitle = getString(R.string.top_paid_apps)
                }
                else -> onNavigationItemSelected(item)
            }
            downloadUrl(feedUrl, feedTitle)
            return true
        }
    }
}

