# **SharedPreferences**

## Overview
If you want to save a small collection of key-values, then you should use `SharedPreferences`. A `SharedPreferences` objects points to a file containing key-value pairs & methods that allow you to read from & write to **shared preferences**. Each **shared preferences** file is managed by the framework & can be private or public.

## Code Example
Open the `bottom-navigation-shared-preferences` directory provided to you in **Android Studio**. 

Lets take a look at what is happening...

### NotificationsFragment

You can create a new **shared preferences** file or access an existing one by calling one of the following methods:
- `getSharedPreferences()` - use if you need multiple **shared preferences** files. You can call this from any `Context` in your application. 
- `getPreferences()` - use if you only need one **shared preferences** file for the **activity**. This retrieves a default **shared preferences** file that belongs to the **activity**. Also, you do not need to supply a name.

```kotlin
class NotificationsFragment : Fragment() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var swToggleNotifications: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!

        val view: View = inflater.inflate(R.layout.fragment_notifications, container, false)

        swToggleNotifications = view.findViewById(R.id.sw_toggle_notifications)
        swToggleNotifications.setOnCheckedChangeListener(SwitchOnCheckChangeListener())

        val isNotifications: Boolean =
            sharedPref.getBoolean(getString(R.string.saved_notifications_key), false)
        swToggleNotifications.isChecked = isNotifications == true

        return view
    }

    inner class SwitchOnCheckChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
            when (buttonView.id) {
                R.id.sw_toggle_notifications -> {
                    sharedPref.edit()
                        .putBoolean(getString(R.string.saved_notifications_key), isChecked).apply()
                }
            }
        }
    }
}
```

### Write to SharedPreferences
To write to a **shared preferences** file, create a `SharedPreferences.Editor` by calling the `edit()` method on your `SharedPreferences`. Pass a key-value into one of the available `put()` methods, then call the `apply()` method to save the changes. The `apply()` method immediately changes the in-memory `SharedPreferences` object & writes the updates to disk asynchronously.  

### Read from SharedPreferences
To read from a **shared preferences** file, pass a key into one of the available `get()` methods. If a key is not present, you can pass an optional default value to return.
