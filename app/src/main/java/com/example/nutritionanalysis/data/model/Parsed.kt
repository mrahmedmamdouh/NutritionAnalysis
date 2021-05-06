package com.example.nutritionanalysis.data.model

import android.os.Parcelable
import com.example.nutritionanalysis.data.model.Nutrient
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Parsed (

    val quantity : Int,
    val measure : String,
    val foodMatch: String,
    val weight: Int,
    val nutrients : @RawValue Nutrient
) : Parcelable