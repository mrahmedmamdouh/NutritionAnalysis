package com.example.nutritionanalysis.data.repository

import com.example.nutritionanalysis.BuildConfig
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.response.ApiInterface
import com.example.nutritionanalysis.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getAnalysis(ingredient : RequestPayload) : NutritionResponse {
        return apiInterface.analyzeNutrition(BuildConfig.APP_ID, BuildConfig.APP_KEY, ingredient)
    }
}