package com.ilih.skyengdict.domain.dto

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("text")
    private val text: String,
    @SerializedName("meanings")
    private val meanings: List<MeaningDto>
)
