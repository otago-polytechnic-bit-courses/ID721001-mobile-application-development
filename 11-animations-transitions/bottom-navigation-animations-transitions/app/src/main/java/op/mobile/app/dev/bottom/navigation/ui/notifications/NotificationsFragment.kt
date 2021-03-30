package op.mobile.app.dev.bottom.navigation.ui.notifications

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import op.mobile.app.dev.bottom.navigation.R

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