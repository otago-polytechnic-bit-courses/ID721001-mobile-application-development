package op.mobile.app.dev.login.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import op.mobile.app.dev.login.model.Login

@Database(entities = [Login::class], version = 1, exportSchema = false)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): ILoginDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDb? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LoginDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDb::class.java,
                    "database"
                )
                    .addCallback(LoginDbCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class LoginDbCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDb(database.loginDao())
                }
            }
        }

        suspend fun populateDb(loginDao: ILoginDao) {
            loginDao.deleteAll()
            var login = Login("john.doe", "P@ssw0rd123")
            loginDao.insert(login)
            login = Login("jane.doe", "P@ssw0rd123")
            loginDao.insert(login)
        }
    }
}