package op.mobile.dev.restaurant.customer.feedback

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
abstract class RestaurantDB : RoomDatabase() {

    abstract val restaurantDAO: RestaurantDAO

    companion object {

        @Volatile
        private var INSTANCE: RestaurantDB? = null

        fun getInstance(ctx: Context): RestaurantDB {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            ctx.applicationContext,
                            RestaurantDB::class.java,
                            "restaurant_history_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
