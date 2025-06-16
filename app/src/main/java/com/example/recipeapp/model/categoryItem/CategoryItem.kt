package com.example.recipeapp.model.categoryItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "categories")
data class CategoryItem(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, // Room ID, not from API

    @ColumnInfo(name = "id_category")
    @SerializedName("idCategory")
    val idCategory: String,

    @ColumnInfo(name = "name")
    @SerializedName("strCategory")
    val strCategory: String,

    @ColumnInfo(name = "thumb")
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String,

    @ColumnInfo(name = "description")
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String
)