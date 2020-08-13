package com.example.myfood.Repository.Food

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myfood.db.Food.FoodDao
import com.example.myfood.db.Food.FoodDetails
import com.example.myfood.db.Restaurant.RestaurantDao
import com.example.myfood.db.Restaurant.RestaurantDetails

class FoodRepository(private val foodDao: FoodDao) {

    val allFoods: LiveData<List<FoodDetails>> = foodDao.getFoods()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(foodDetails: FoodDetails) {
        foodDao.insertFood(foodDetails)
    }
}