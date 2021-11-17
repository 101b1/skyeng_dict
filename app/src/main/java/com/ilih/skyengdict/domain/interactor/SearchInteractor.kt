package com.ilih.skyengdict.domain.interactor

import com.ilih.skyengdict.domain.data.SearchService
import com.ilih.skyengdict.domain.dto.SearchError
import com.ilih.skyengdict.domain.dto.SearchResult
import com.ilih.skyengdict.domain.dto.SearchSuccess
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SearchInteractor
@Inject
constructor(private val searchService: SearchService) {

    private val searchSubject: BehaviorSubject<SearchResult> = BehaviorSubject.create()
    private var searchDisposable: Disposable? = null
    private var currentQuery: String = ""
    private var pagesDownloaded = 0
    private var hasMorePages = true

    fun getObservable(): Observable<SearchResult> = searchSubject

    fun searchWord(query: String) {
        searchDisposable?.dispose()
        if (hasMorePages){
            var pageNumber = 1
            if (query == currentQuery){
                pageNumber = pagesDownloaded+1
            } else{
                currentQuery = query
            }
            searchDisposable = searchService.search(query, pageNumber)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.isEmpty()){
                            hasMorePages = false
                        } else {
                            pagesDownloaded +=1
                            searchSubject.onNext(SearchSuccess(it))
                        }
                    },
                    {
                        searchSubject.onNext(SearchError(it.localizedMessage ?: "Server error"))
                    }
                )
        }
    }
}