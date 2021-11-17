package com.ilih.skyengdict.domain.dto

import com.google.gson.annotations.SerializedName

data class TranslationDto(
    @SerializedName("text")
    private val text: String,
    @SerializedName("note")
    private val note: String
)
