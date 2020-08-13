package com.example.myfood.Repository.Restaurant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myfood.db.ResDataBase
import com.example.myfood.db.Restaurant.RestaurantDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ResRepository

    val allRestaurant: LiveData<List<RestaurantDetails>>

    init {

        val restaurantDao = ResDataBase.getDatabase(application, viewModelScope).getRestaurantDao()
        repository = ResRepository(restaurantDao)
        allRestaurant = repository.allRestaurant
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(restaurantDetails: RestaurantDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(restaurantDetails)
    }
}