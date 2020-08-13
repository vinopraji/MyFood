package com.example.myfood.db.Restaurant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_table")
data class RestaurantDetails(
    @ColumnInfo(name = "name") val resName: String?,
    @ColumnInfo(name = "rating") val resRating: String?,
    @ColumnInfo(name = "day") val resDay: String?,
    @ColumnInfo(name = "time") val resTime: String?,
    @ColumnInfo(name = "contact") val resContact: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

}
