package op.mobile.app.dev.login.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.login.database.LoginDao
import op.mobile.app.dev.login.model.Login

class LoginRepository (private val loginDao: LoginDao) {
    val allLoginDetails: Flow<List<Login>> = loginDao.getAllLoginDetails()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(login: Login) {
        loginDao.insert(login)
    }
}