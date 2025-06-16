package com.example.recipeapp.presentation.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.categoryItem.CategoryItem
import com.example.recipeapp.model.mealItem.MealItem
import com.example.recipeapp.model.subCategoryItem.Meal
import com.example.recipeapp.repository.CategoryRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CategoryRepository): ViewModel() {

    val allCategories: LiveData<List<CategoryItem>> = repository.allCategories
    val allMeals: LiveData<List<MealItem>> = repository.allMeals

    private val _mealDetail = MutableLiveData<Meal?>()
    val mealDetail: LiveData<Meal?> = _mealDetail

    init {
        viewModelScope.launch {
            repository.fetchCategoriesAndSaveToDB()
        }
    }

    fun fetchSpecificMeals(category: String) {
        viewModelScope.launch {
            repository.fetchSpecificMealsAndSaveToDB(category)
        }
    }

    fun fetchMealDetailById(id: String) {
        viewModelScope.launch {
            val meal = repository.fetchMealDetailByIdAndSaveToDB(id)
            _mealDetail.postValue(meal)
        }
    }

}

class HomeViewModelFactory(
    private val repository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
