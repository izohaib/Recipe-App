package com.example.recipeapp.model.categoryItem

import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("categories")
    val categoryItems: List<CategoryItem>

)