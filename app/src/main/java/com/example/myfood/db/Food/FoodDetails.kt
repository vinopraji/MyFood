package com.example.myfood.db.Food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "food_table")
data class FoodDetails(
    @ColumnInfo(name = "food_name") val foddName: String?,
    @ColumnInfo(name = "food_price") val foodPrice: String?,
    @ColumnInfo(name = "food_discrption") val foodDiscrption: String?,
    @ColumnInfo(name = "food_count") val foodCount: String?
) {

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}