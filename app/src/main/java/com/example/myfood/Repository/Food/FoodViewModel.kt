package com.example.myfood.Repository.Food

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myfood.Repository.Restaurant.ResRepository
import com.example.myfood.db.Food.FoodDetails
import com.example.myfood.db.ResDataBase
import com.example.myfood.db.Restaurant.RestaurantDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: FoodRepository

    val allFoods: LiveData<List<FoodDetails>>

    init {

        val foodDao = ResDataBase.getDatabase(application, viewModelScope).getFoodDao()
        repository = FoodRepository(foodDao)
        allFoods = repository.allFoods
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(foodDetails: FoodDetails) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(foodDetails)
    }
}