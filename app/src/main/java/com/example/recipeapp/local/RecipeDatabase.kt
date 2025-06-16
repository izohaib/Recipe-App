package com.example.recipeapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipeapp.model.categoryItem.CategoryItem
import com.example.recipeapp.model.mealItem.MealItem

//@TypeConverters(CategoryItemConverter::class)
@Database(entities = [CategoryItem::class, MealItem::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

    companion object {
        @Volatile
        private var recipeDatabase: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return recipeDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "app_database"
                ).build()
                recipeDatabase = instance
                instance
            }
        }
    }
}


//@Database(entities = [CategoryItem::class ], version = 1, exportSchema = false)
//abstract class RecipeDatabase: RoomDatabase() {
//    abstract fun recipesDao(): RecipesDao
//
//    companion object {
//        @Volatile private var recipeDatabase: RecipeDatabase? = null
//
//        fun getDatabase(context: Context): RecipeDatabase {
//            if (recipeDatabase == null) {
//                recipeDatabase = Room.databaseBuilder(
//                    context.applicationContext,
//                    RecipeDatabase::class.java,
//                    "app_database"
//                ).build()
//            }
//            return recipeDatabase!!
//        }
//    }
//}