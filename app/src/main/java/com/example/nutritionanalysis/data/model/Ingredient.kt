package com.example.nutritionanalysis.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Ingredient (
    val text : String,
     val parsed : ArrayList<Parsed>
) : Parcelable