package com.ilih.skyengdict.ui.search

import androidx.lifecycle.LiveData
import com.ilih.skyengdict.domain.dto.SearchResult
import com.ilih.skyengdict.domain.dto.SearchResultDto

interface SearchViewModel {
    fun getListener(): SearchView.Listener
    fun getData(): LiveData<List<SearchResultDto>>
    fun getState(): LiveData<SearchState>
}