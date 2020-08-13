package com.example.myfood.Repository.Restaurant

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myfood.db.Restaurant.RestaurantDao
import com.example.myfood.db.Restaurant.RestaurantDetails

class ResRepository (private val restaurantDao: RestaurantDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRestaurant: LiveData<List<RestaurantDetails>> = restaurantDao.getRestaurant()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(restaurantDetails: RestaurantDetails) {
        restaurantDao.insertRestaurant(restaurantDetails)
    }
}