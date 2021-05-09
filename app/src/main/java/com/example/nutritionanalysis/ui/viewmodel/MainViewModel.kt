package com.example.nutritionanalysis.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritionanalysis.data.model.Ingredient
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.data.repository.MainRepository
import com.example.nutritionanalysis.di.IoDispatcher
import com.example.nutritionanalysis.utils.Resource
import com.example.nutritionanalysis.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val repositoriesLiveData = SingleLiveEvent<Resource<NutritionResponse>>()
    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(ioDispatcher + job)

    fun fetchProducts(ingredients: RequestPayload) {
        viewModelScope.launch {
            repositoriesLiveData.value = Resource.Loading(null)
            try {
                val data = ioScope.async {
                    return@async repository.getAnalysis(ingredients)
                }.await()
                repositoriesLiveData.value = Resource.Success(data)
            } catch (e: Exception) {
                repositoriesLiveData.value = Resource.Error(e)
                Timber.e(e)
            }
        }
    }

    fun roundDouble(double: Double) : Int{
        return double.roundToInt()
    }

}