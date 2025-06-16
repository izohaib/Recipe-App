package com.example.recipeapp.presentation.homeScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.R
import com.example.recipeapp.adapter.MainCategoryAdapter
import com.example.recipeapp.adapter.SubCategoryAdapter
import com.example.recipeapp.databinding.ActivityHomeBinding
import com.example.recipeapp.local.RecipeDatabase
import com.example.recipeapp.model.categoryItem.CategoryItem
import com.example.recipeapp.model.mealItem.MealItem
import com.example.recipeapp.netowrk.RetrofitClient
import com.example.recipeapp.presentation.detialScreen.DetailScreen
import com.example.recipeapp.repository.CategoryRepository

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var categoryItems: List<CategoryItem> = emptyList()
    private var mealsItems: List<MealItem> = emptyList()


    var isFirstLaunch = true

    var categoryName = "Pork"
    val mainCategoryAdapter = MainCategoryAdapter { categoryName ->
        this@HomeActivity.categoryName = categoryName
        binding.categoryName.text = categoryName
        if (isFirstLaunch) {
            isFirstLaunch = false
            viewModel.fetchSpecificMeals("Pork")
        } else {
//            subCategoryAdapter.setData(emptyList())
            viewModel.fetchSpecificMeals(categoryName)
        }
    }

    var subCategoryAdapter = SubCategoryAdapter { id ->
        // Go to DetailScreen and pass ID
        val intent = Intent(this, DetailScreen::class.java)
        intent.putExtra("meal_id", id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom // This line adds the navigation bar padding at the bottom
            )
            insets
        }

        // 1. Setup DAO, repository, and factory
        val dao = RecipeDatabase.Companion.getDatabase(applicationContext).recipesDao()
        val apiService = RetrofitClient.api
        val repository = CategoryRepository(apiService, dao)
        val factory = HomeViewModelFactory(repository)

        // viewmodel
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        viewModel.allCategories.observe(this) { list ->
            Log.d("OBSERVE", "Categories size: ${list.size}")
            categoryItems = list
            mainCategoryAdapter.setData(categoryItems)
        }

        viewModel.allMeals.observe(this) { meals ->
            Log.d("OBSERVE", "meals size: ${meals.size}")
            mealsItems = meals
            subCategoryAdapter.setData(mealsItems)
        }

        binding.rvMainCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMainCategory.adapter = mainCategoryAdapter

        binding.rvSubCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvSubCategory.adapter = subCategoryAdapter

    }
}