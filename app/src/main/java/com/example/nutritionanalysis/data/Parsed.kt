package com.example.nutritionanalysis.data

import android.os.Parcelable
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