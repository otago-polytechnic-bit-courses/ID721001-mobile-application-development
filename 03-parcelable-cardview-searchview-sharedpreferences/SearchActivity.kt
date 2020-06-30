package op.graysono.practical03.activities

import android.app.SearchManager
import android.app.SearchableInfo
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.preference.PreferenceManager
import op.graysono.practical03.R

class SearchActivity : BaseActivity() {

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        /**
         * Implement internal function. Display the custom App Bar/toolbar
         */
        displayToolbar(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        /**
         * searchManager - retrieve a SearchManager for handling searches
         * searchInfo - gets information about the searchable activity. componentName is an identifier for
         * a specific app component, for example, Activity
         *
         * Resources:
         * - https://developer.android.com/reference/android/content/Context#SEARCH_SERVICE
         * - https://developer.android.com/reference/android/app/SearchManager#getSearchableInfo(android.content.ComponentName)
         * - https://developer.android.com/reference/android/content/ComponentName
         */
        val searchManager: SearchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchableInfo: SearchableInfo? = searchManager.getSearchableInfo(componentName)

        /**
         * Find menu by its id and cast as a SearchView
         * setSearchableInfo - used to display labels, hints, suggestions, create intents for launching search results & controlling
         * other affordances such as voice button
         * isIconified - returns the current iconified state of the SearchView. If true, the SearchView is iconified,
         * if false, the search field is visible
         *
         *  Resources:
         *  - https://developer.android.com/reference/android/widget/SearchView#setSearchableInfo(android.app.SearchableInfo)
         *  - https://developer.android.com/reference/android/widget/SearchView#isIconified()
         */
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchableInfo)
        searchView.isIconified = false

        /**
         * setOnQueryTextListener - sets a listener for user actions within the SearchView
         * onQueryTextSubmit - called when the user submits a query. This could be due to a key press or submit button
         * getDefaultSharedPreferences - gets a SharedPreference instance that points to the default file that is used
         * by the preference framework in the given context, i.e. SearchActivity
         *
         * In the example below, the value from the SearchView is being put into SharedPreferences
         *
         * clearFocus - called when the View wants to give up focus
         * finish - called when the activity is done & should be closed
         *
         * Return true if SearchView has been submitted
         *
         * Resources:
         * - https://developer.android.com/reference/android/widget/SearchView#setOnQueryTextListener(android.widget.SearchView.OnQueryTextListener)
         * - https://developer.android.com/reference/android/widget/SearchView.OnQueryTextListener#onQueryTextSubmit(java.lang.String)
         * - https://developer.android.com/reference/android/preference/PreferenceManager#getDefaultSharedPreferences(android.content.Context)
         * - https://developer.android.com/reference/android/view/View#clearFocus()
         * - https://developer.android.com/reference/android/app/Activity#finish()
         */
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val sharedPref: SharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(this@SearchActivity)
                sharedPref.edit().putString("album_query", query).apply()
                searchView.clearFocus()
                finish()
                return true
            }

            /**
             * Called when the query text is changed by the user
             * params: newText - the new content of the query text field
             *
             * Resources:
             * - https://developer.android.com/reference/android/widget/SearchView.OnQueryTextListener#onQueryTextChange(java.lang.String)
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        /**
         * Sets a listener to inform when the user closes the SearchView
         */
        searchView.setOnCloseListener {
            finish()
            false
        }

        return true
    }
}
