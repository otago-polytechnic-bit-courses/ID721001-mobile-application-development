package op.mobile.dev.restaurant.experience.tracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RestaurantDAO {

    @Insert
    suspend fun insert(restaurant: Restaurant)

    @Update
    suspend fun update(restaurant: Restaurant)

    @Query("SELECT * from restaurant_history_table WHERE restaurantEntryId = :key")
    suspend fun get(key: Long): Restaurant?

    @Query("DELETE FROM restaurant_history_table")
    suspend fun clear()

    @Query("SELECT * FROM restaurant_history_table ORDER BY restaurantEntryId DESC")
    fun getAllRestaurantData(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM restaurant_history_table ORDER BY restaurantEntryId DESC LIMIT 1")
    suspend fun getRestaurantData(): Restaurant?
}
