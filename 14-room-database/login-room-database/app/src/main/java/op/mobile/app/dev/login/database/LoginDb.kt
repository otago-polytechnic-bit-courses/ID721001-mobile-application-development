package op.mobile.app.dev.login.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import op.mobile.app.dev.login.model.Login

@Database(entities = [Login::class], version = 1, exportSchema = false)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): LoginDao

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
                    "LOGIN_DATABASE"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}