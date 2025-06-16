package com.example.recipeapp.netowrk

import com.example.recipeapp.model.categoryItem.ApiResponse
import com.example.recipeapp.model.mealItem.Meal
import com.example.recipeapp.model.subCategoryItem.SubCategoryDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): ApiResponse
    @GET("filter.php")
    suspend fun getSpecificItem(@Query("c") category: String): Meal
    @GET("lookup.php")
    suspend fun getItemDetailById(@Query("i") id: String): SubCategoryDetails
}