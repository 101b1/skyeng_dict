package com.ilih.skyengdict.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MeaningDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    @SerializedName("translation")
    val translation: TranslationDto,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("")
    val soundUrl: String
) : Parcelable
