package com.ilih.skyengdict.domain.dto

sealed class SearchResult
data class SearchSuccess(val results: List<SearchResultDto>): SearchResult()
data class SearchError(val error: String): SearchResult()
