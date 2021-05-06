package com.example.nutritionanalysis.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Nutrient (

    val ENERC_KCAL : @RawValue NDetails
) : Parcelable