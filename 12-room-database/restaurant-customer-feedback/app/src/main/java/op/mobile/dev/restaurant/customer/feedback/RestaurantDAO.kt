package op.mobile.dev.restaurant.customer.feedback

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
    suspend  fun update(restaurant: Restaurant)

    @Query("SELECT * from restaurant_data_table WHERE restaurantEntryId = :key")
    suspend fun get(key: Long): Restaurant?

    @Query("DELETE FROM restaurant_data_table")
    suspend fun clear()

    @Query("SELECT * FROM restaurant_data_table ORDER BY restaurantEntryId DESC")
    fun getAllRestaurantData(): LiveData<List<Restaurant>>

    @Query("SELECT * FROM restaurant_data_table ORDER BY restaurantEntryId DESC LIMIT 1")
    suspend fun getRestaurantData(): Restaurant?
}
