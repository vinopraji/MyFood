package com.example.myfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.R
import com.example.myfood.Repository.Food.FoodViewModel
import com.example.myfood.Repository.Restaurant.RestaurantViewModel
import com.example.myfood.db.Food.FoodDetails
import com.example.myfood.db.Restaurant.RestaurantDetails

class MainActivity : AppCompatActivity() {
    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = RestaurantAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val details =
            RestaurantDetails(
                "vino",
                "67",
                "all",
                "6 to 7",
                "9363743409"
            )
        val food= FoodDetails("Biriyani","100","Checken","")
        // Get a new or existing ViewModel from the View)ModelProvider.
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        foodViewModel.insert(food)
       /* restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        restaurantViewModel.insert(details)*/


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
       /* restaurantViewModel.allRestaurant.observe(this, Observer { restaurant ->
            // Update the cached copy of the words in the adapter.
            restaurant?.let {
                adapter.setRestaurant(it) }
        })*/

        foodViewModel.allFoods.observe(this, Observer { food ->
            // Update the cached copy of the words in the adapter.
            food?.let {
                adapter.setFood(it) }
        })
    }

}
