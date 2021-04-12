package op.mobile.app.dev.login

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import op.mobile.app.dev.login.database.ILoginDao
import op.mobile.app.dev.login.database.LoginDb
import op.mobile.app.dev.login.model.Login
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LoginDaoTest {
    private lateinit var db: LoginDb
    private lateinit var dao: ILoginDao

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, LoginDb::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.loginDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun insertLoginDetailAndGetSecondLoginDetail() = runBlocking {
        var login = Login("john.doe", "P@ssw0rd123")
        dao.insert(login)
        login = Login("jane.doe", "P@ssw0rd123")
        dao.insert(login)
        val allLoginDetails = dao.getAll()
    }
}