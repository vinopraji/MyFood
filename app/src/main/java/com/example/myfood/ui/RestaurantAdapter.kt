package com.example.myfood.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfood.R
import com.example.myfood.db.Food.FoodDetails
import com.example.myfood.db.Restaurant.RestaurantDetails

class RestaurantAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
 //   private var restaurantDetails = emptyList<RestaurantDetails>() // Cached copy of words
    private var foodDetails = emptyList<FoodDetails>() // Cached copy of words


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return RestaurantViewHolder(itemView)
    }
    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.text1)
        val rate: TextView = itemView.findViewById(R.id.text2)
        val day: TextView = itemView.findViewById(R.id.text3)
        val time: TextView = itemView.findViewById(R.id.text4)
        val contact: TextView = itemView.findViewById(R.id.text5)
    }

    override fun getItemCount()= foodDetails.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val current = foodDetails[position]
        holder.name.text = current.foddName
        holder.rate.text = current.foodPrice
        holder.day.text = current.foodDiscrption
        holder.time.text = current.foodCount
      //  holder.contact.text = current.resContact
    }
   /* internal fun setRestaurant(restaurants: List<RestaurantDetails>) {
        this.restaurantDetails = restaurants
        notifyDataSetChanged()
    }*/
    internal fun setFood(foods: List<FoodDetails>) {
        this.foodDetails = foods
        notifyDataSetChanged()
    }
}