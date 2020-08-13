package com.example.myfood.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myfood.db.Food.FoodDao
import com.example.myfood.db.Food.FoodDetails
import com.example.myfood.db.Restaurant.RestaurantDao
import com.example.myfood.db.Restaurant.RestaurantDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [RestaurantDetails::class, FoodDetails::class],
    version = 1
)
abstract class ResDataBase  : RoomDatabase(){

    abstract fun getRestaurantDao(): RestaurantDao
    abstract fun getFoodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: ResDataBase? = null


        fun getDatabase(context: Context,scope: CoroutineScope): ResDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ResDataBase::class.java,
                    "Food_database")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope)
                    )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.getRestaurantDao())
                    }
                }

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase1(database.getFoodDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(restaurantDao: RestaurantDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            restaurantDao.deleteAllRestaurant()

        }

        fun populateDatabase1(foodDao: FoodDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            foodDao.deleteAllFoods()

        }

    }
}