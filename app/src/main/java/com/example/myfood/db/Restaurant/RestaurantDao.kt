package com.example.myfood.db.Restaurant

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RestaurantDao {





    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertRestaurant(restaurantdetails: RestaurantDetails)

    @Insert
    fun insertAll(vararg restaurantdetails: RestaurantDetails)

    @Query("SELECT * from restaurant_table")
    fun getRestaurant(): LiveData<List<RestaurantDetails>>


    @Query("SELECT * FROM restaurant_table WHERE uid IN (:userIds)")
    fun getAllByRestaurantIds(userIds: IntArray): List<RestaurantDetails>


    @Query("SELECT * FROM restaurant_table WHERE name LIKE :name")
    fun getByRestaurantName(name: String): RestaurantDetails


    @Query("DELETE FROM restaurant_table")
    fun deleteAllRestaurant()

    @Delete
    fun delete(restaurantdetails: RestaurantDetails)


}