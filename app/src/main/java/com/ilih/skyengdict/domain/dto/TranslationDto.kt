package com.ilih.skyengdict.domain.dto

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TranslationDto(
    @SerializedName("text")
    val text: String,
    @SerializedName("note")
    val note: String
) : Parcelable