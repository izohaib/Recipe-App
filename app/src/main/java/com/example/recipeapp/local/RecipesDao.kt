package com.example.recipeapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.model.categoryItem.CategoryItem
import com.example.recipeapp.model.mealItem.MealItem

@Dao
interface RecipesDao {

    // FIX: Insert list of items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<CategoryItem>)

    // FIX: Return LiveData (for observing in ViewModel/Activity)
    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun getAllCategories(): LiveData<List<CategoryItem>>

    // meal Table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: List<MealItem>)

    @Query("SELECT * FROM meals ORDER BY idMeal DESC")
    fun getAllMeal(): LiveData<List<MealItem>>

    // New: Clear the meals table before inserting new data
    @Query("DELETE FROM meals")
    suspend fun clearMeals()
}
