package op.mobile.app.dev.login.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.login.database.ILoginDao
import op.mobile.app.dev.login.model.Login

class LoginRepository (private val loginDao: ILoginDao) {
    val allLoginDetails: Flow<List<Login>> = loginDao.getAll()

    @WorkerThread
    suspend fun insertLoginDetail(login: Login) {
        loginDao.insert(login)
    }
}