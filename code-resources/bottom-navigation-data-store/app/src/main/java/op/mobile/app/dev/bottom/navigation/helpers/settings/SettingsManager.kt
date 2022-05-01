package op.mobile.app.dev.bottom.navigation.helpers.settings

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsManager(context: Context) {
    private val dataStore: DataStore<Preferences> =
        context.applicationContext.createDataStore(DATA_STORE_NAME)

    val uiModeFlow: Flow<UIMode> = dataStore.data.catch {
        if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        when (it[IS_DARK_MODE] ?: false) {
            true -> UIMode.DARK
            false -> UIMode.LIGHT
        }
    }

    suspend fun setUIMode(uiMode: UIMode) {
        dataStore.edit {
            it[IS_DARK_MODE] = when (uiMode) {
                UIMode.LIGHT -> false
                UIMode.DARK -> true
            }
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "settings.pref"
        private val IS_DARK_MODE = preferencesKey<Boolean>("is_dark_mode")
    }
}