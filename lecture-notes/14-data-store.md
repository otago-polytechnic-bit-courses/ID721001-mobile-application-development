# **14: Data Store**

## Overview

**DataStore** is a way to store data as **key-value** pairs. **DataStore** uses coroutines and **Flow** to store data asynchronously, consistently, and transactionally. 

There are two ways to implement **DataStore** in your application:
- **Preferences DataStore** stores and accesses data using keys
- **Proto DataStore** stores data as instances of a custom data type

You will use **Preferences DataStore**.

## Code Example

Lets take a look at the code example. Make sure you have the following dependency in your `build.gradle (Module)`:

`implementation 'androidx.datastore:datastore-preferences:1.0.0-alpha02'`

## UIMode

You will have two modes - light and dark. We can put these into an `Enum` class.

```kotlin
enum class UIMode {
    LIGHT,
    DARK
}
```

## SettingsManager

```kotlin
// Imports
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// We need a reference to the context, i.e., SettingsFragment. Note: you can use this class with an Activity
class SettingsManager(context: Context) {

    // Create a new data store - it will store the mode as key/value pairs, i.e., is_dark_mode: 0 or is_dark_mode: 1
    private val dataStore: DataStore<Preferences> =
        context.applicationContext.createDataStore(DATA_STORE_NAME) // This constant is declared in a companion object at the bottom of this class

    val uiModeFlow: Flow<UIMode> = dataStore.data.catch {
        // Catch any issues that may occur when observing
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences()) // Empty the data store
        } else {
            throw it
        }
    }.map {
        // Elvis operator (?:)...shorthand ternary operator
        
        // If the is_dark_mode value is 1, then UIMode is set to DARK...if is_dark_mode value is 0, then UIMode is set to LIGHT
        // Remember, these UIMode values are set in the UIMode enum class
        when (it[IS_DARK_MODE] ?: false) {
            true -> UIMode.DARK
            false -> UIMode.LIGHT
        }
    }

    // While observing, set the UIMode that is checked, i.e., this value will be based on whether or not the Switch widget is checked
    suspend fun setUIMode(uiMode: UIMode) {
        dataStore.edit {
            it[IS_DARK_MODE] = when (uiMode) {
                UIMode.LIGHT -> false
                UIMode.DARK -> true
            }
        }
    }

    // The companion object is used to access members of a class without creating 
    // an instance of a class. The companion object is only tied to this class. Also, 
    // you can relate a companion object to a static method. However, internally,
    // they are very different.
    companion object {
        private const val DATA_STORE_NAME = "settings.pref"
        private val IS_DARK_MODE = preferencesKey<Boolean>("is_dark_mode")
    }
}
```

## SettingsFragment

```kotlin
class SettingsFragment : Fragment() {
    private lateinit var swToggleDarkMode: SwitchCompat
    private lateinit var settingsManager: SettingsManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        swToggleDarkMode = view.findViewById(R.id.sw_toggle_dark_mode)

        settingsManager = SettingsManager(requireContext())
        observeUIPreferences()

        swToggleDarkMode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                when (isChecked) {
                    true -> settingsManager.setUIMode(UIMode.DARK)
                    false -> settingsManager.setUIMode(UIMode.LIGHT)
                }
            }
        }

        return view
    }

    private fun setCheckedUIMode(uiMode: UIMode?) {
        when (uiMode) {
            UIMode.LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                swToggleDarkMode.isChecked = false
            }
            UIMode.DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                swToggleDarkMode.isChecked = true
            }
        }
    }

    private fun observeUIPreferences() {
        settingsManager.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            setCheckedUIMode(it)
        }
    }
}
```

**Resources:**
- https://developer.android.com/topic/libraries/architecture/datastore
