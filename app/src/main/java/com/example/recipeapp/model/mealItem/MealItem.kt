package com.example.recipeapp.model.mealItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealItem(
    @PrimaryKey
    val idMeal: String, // Unique meal ID

    @ColumnInfo(name = "meal_name")
    val strMeal: String,

    @ColumnInfo(name = "meal_thumb")
    val strMealThumb: String,

//    @ColumnInfo(name = "meal_category")
//    val category: String // This is the key to filtering meals
)
