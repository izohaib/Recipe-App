package com.example.recipeapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.recipeapp.local.RecipesDao
import com.example.recipeapp.model.categoryItem.CategoryItem
import com.example.recipeapp.model.mealItem.MealItem
import com.example.recipeapp.model.subCategoryItem.Meal
import com.example.recipeapp.netowrk.ApiService

class CategoryRepository(
    private val api: ApiService,
    private val dao: RecipesDao
) {
    val allCategories: LiveData<List<CategoryItem>> = dao.getAllCategories()
    val allMeals: LiveData<List<MealItem>> = dao.getAllMeal()

    suspend fun fetchCategoriesAndSaveToDB() {
        try {
            val response = api.getCategories()  // ApiResponse
            dao.insertAll(response.categoryItems)  // insert whole list
        } catch (e: Exception) {
            Log.e("Repository", "Error: ${e.message}")
        }
    }

    suspend fun fetchSpecificMealsAndSaveToDB(category: String){
        try {
            val response = api.getSpecificItem(category)  // ApiResponse
            dao.clearMeals()
            dao.insertMeal(response.meals)  // insert whole list
        } catch (e: Exception) {
            Log.e("Repository", "Error: ${e.message}")
        }
    }

    suspend fun fetchMealDetailByIdAndSaveToDB(subCatId: String): Meal? {
        return try {
            val response = api.getItemDetailById(subCatId)
            val meal = response.meals.firstOrNull()
            // dao.insertMeal(listOf(meal))
            return meal
        } catch (e: Exception) {
            Log.e("Repository", "Error fetching meal detail: ${e.message}")
            null
        }
    }

}
