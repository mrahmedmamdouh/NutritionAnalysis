package com.example.nutritionanalysis.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionanalysis.data.model.Ingredient
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.data.repository.MainRepository
import com.example.nutritionanalysis.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val job = SupervisorJob()
    val repositoriesLiveData = MutableLiveData<NutritionResponse>()
    private val ioScope = CoroutineScope(ioDispatcher + job)

    fun fetchProducts(ingredients: RequestPayload) {
        viewModelScope.launch {
            try {
                val data = ioScope.async {
                    return@async repository.getAnalysis(ingredients)
                }.await()
                repositoriesLiveData.postValue(data)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}