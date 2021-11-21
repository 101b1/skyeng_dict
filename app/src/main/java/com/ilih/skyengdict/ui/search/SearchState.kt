package com.ilih.skyengdict.ui.search

import com.ilih.skyengdict.domain.dto.SearchResultDto

sealed class SearchState
object SearchSuccess: SearchState()
data class SearchError(val error: String): SearchState()
object SearchLoading: SearchState()
object SearchNoResults: SearchState()
