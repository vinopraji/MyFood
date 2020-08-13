package com.example.myfood.db.Food

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFood(foodDetails: FoodDetails)

    @Insert
    fun insertAll(vararg foodDetails: FoodDetails)


    @Query("SELECT * from food_table")
    fun getFoods(): LiveData<List<FoodDetails>>

    @Query("DELETE FROM food_table")
    fun deleteAllFoods()

}