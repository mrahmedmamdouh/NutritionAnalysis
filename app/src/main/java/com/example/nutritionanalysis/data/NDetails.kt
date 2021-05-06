package com.example.nutritionanalysis.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NDetails (

    val label : String,
    val quantity : Double,
    val unit : String
) : Parcelable