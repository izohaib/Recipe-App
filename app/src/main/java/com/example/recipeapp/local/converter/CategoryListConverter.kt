package com.example.recipeapp.local.converter

import com.example.recipeapp.model.categoryItem.CategoryItem
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryItemConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCategoryItemList(categoryItems: List<CategoryItem>?): String {
        return gson.toJson(categoryItems)
    }

    @TypeConverter
    fun toCategoryItemList(categoryItemsString: String): List<CategoryItem> {
        val type = object : TypeToken<List<CategoryItem>>() {}.type
        return gson.fromJson(categoryItemsString, type)
    }
}
