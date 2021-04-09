package op.mobile.app.dev.login.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.login.model.Login

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(login: Login)

    @Query("SELECT * FROM login ORDER BY username ASC")
    fun getAllLoginDetails(): Flow<List<Login>>
}