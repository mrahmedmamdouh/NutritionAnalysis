package com.example.nutritionanalysis.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutritionResponse (

    val uri : String,
    val calories: Int,
    val totalWeight : Double,
    val ingredients : ArrayList<Ingredient>,
    val totalNutrients : TotalNutrient,
    val totalDaily : TotalDaily
) : Parcelable