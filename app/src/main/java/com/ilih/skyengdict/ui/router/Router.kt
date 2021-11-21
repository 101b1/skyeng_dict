package com.ilih.skyengdict.ui.router

import com.ilih.skyengdict.domain.dto.SearchResultDto

interface Router {
    fun navToMeaning(searchResultDto: SearchResultDto)
    fun navBack()
}