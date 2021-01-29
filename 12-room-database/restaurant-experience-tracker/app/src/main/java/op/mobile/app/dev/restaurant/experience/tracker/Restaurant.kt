package op.mobile.app.dev.restaurant.experience.tracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_history_table")
data class Restaurant(
        @PrimaryKey(autoGenerate = true)
        var restaurantEntryId: Long = 0L,

        @ColumnInfo(name = "start_time_milli")
        val startTimeMilli: Long = System.currentTimeMillis(),

        @ColumnInfo(name = "end_time_milli")
        var endTimeMilli: Long = startTimeMilli,

        @ColumnInfo(name = "restaurant_rating")
        var restaurantRating: Int = -1
)