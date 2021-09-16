package op.mobile.app.dev.bottom.navigation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.bottom.navigation.R
import op.mobile.app.dev.bottom.navigation.helpers.settings.SettingsManager
import op.mobile.app.dev.bottom.navigation.helpers.settings.UIMode

class SettingsFragment : Fragment() {
    private lateinit var swToggleDarkMode: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_settings, container, false)
        swToggleDarkMode = view.findViewById(R.id.sw_toggle_dark_mode)

        val settingsManager = SettingsManager(requireContext())
        settingsManager.uiModeFlow.asLiveData().observe(viewLifecycleOwner) {
            setCheckedUIMode(it)
        }

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
}