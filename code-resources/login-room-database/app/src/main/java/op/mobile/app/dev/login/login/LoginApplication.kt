package op.mobile.app.dev.login.login

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import op.mobile.app.dev.login.database.LoginDb
import op.mobile.app.dev.login.repository.LoginRepository

class LoginApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { LoginDb.getDatabase(this, applicationScope) }
    val repository by lazy { LoginRepository(database.loginDao()) }
}
