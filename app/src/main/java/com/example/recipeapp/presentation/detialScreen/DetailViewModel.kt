package com.example.recipeapp.presentation.detialScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.subCategoryItem.Meal
import com.example.recipeapp.repository.CategoryRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val _mealDetail = MutableLiveData<Meal?>()
    val mealDetail: LiveData<Meal?> = _mealDetail

    fun fetchMealDetailById(id: String) {
        viewModelScope.launch {
            val meal = repository.fetchMealDetailByIdAndSaveToDB(id)
            _mealDetail.postValue(meal)
        }
    }
}

class DetailViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}
