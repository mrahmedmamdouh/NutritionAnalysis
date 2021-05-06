package com.example.nutritionanalysis.data.model

import android.os.Parcelable
import com.example.nutritionanalysis.data.model.NDetails
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Nutrient (

    val ENERC_KCAL : @RawValue NDetails
) : Parcelable