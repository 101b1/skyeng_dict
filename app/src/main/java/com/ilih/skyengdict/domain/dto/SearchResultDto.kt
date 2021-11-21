package com.ilih.skyengdict.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResultDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("meanings")
    val meanings: List<MeaningDto>
) : Parcelable
