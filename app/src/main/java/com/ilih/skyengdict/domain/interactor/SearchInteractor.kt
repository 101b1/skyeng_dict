package com.ilih.skyengdict.domain.interactor

import com.ilih.skyengdict.domain.dto.SearchError
import com.ilih.skyengdict.domain.dto.SearchResult
import com.ilih.skyengdict.domain.dto.SearchSuccess
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

interface SearchInteractor {

    fun getObservable(): Observable<SearchResult>
    fun searchWord(query: String, pageNumber: Int)

}