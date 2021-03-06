package com.example.nutritionanalysis.response

import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.utils.Resource
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("api/nutrition-details")
    suspend fun analyzeNutrition(
        @Query("app_id") app_id : String,
        @Query("app_key") app_key : String,
        @Body ingr : RequestPayload
        ) : NutritionResponse
}