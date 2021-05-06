package com.example.nutritionanalysis.data.model

import android.os.Parcelable
import com.example.nutritionanalysis.data.model.NDetails
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TotalDaily (

    val FAT : NDetails,
    val FASAT : NDetails,
    val CHOLE : NDetails,
    val NA : NDetails,
    val CHOCDF : NDetails,
    val FIBTG : NDetails,
    val SUGAR : NDetails,
    val PROCNT : NDetails,
    val VITD : NDetails,
    val CA : NDetails,
    val FE : NDetails,
    val K : NDetails


) : Parcelable