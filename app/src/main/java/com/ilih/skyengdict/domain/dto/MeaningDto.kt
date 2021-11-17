package com.ilih.skyengdict.domain.dto

import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("partOfSpeechCode")
    private val partOfSpeechCode: String,
    @SerializedName("translation")
    private val translation: TranslationDto,
    @SerializedName("previewUrl")
    private val previewUrl: String,
    @SerializedName("imageUrl")
    private val imageUrl: String,
    @SerializedName("transcription")
    private val transcription: String,
    @SerializedName("")
    private val soundUrl: String
)
