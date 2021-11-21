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

class SearchInteractorImpl
@Inject
constructor(private val searchService: SearchService): SearchInteractor {

    private val searchSubject: BehaviorSubject<SearchResult> = BehaviorSubject.create()
    private var searchDisposable: Disposable? = null

    override fun getObservable(): Observable<SearchResult> = searchSubject

    override fun searchWord(query: String, pageNumber: Int) {
        searchDisposable?.dispose()

        searchDisposable = searchService.search(query, pageNumber)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    searchSubject.onNext(SearchSuccess(it))
                },
                {
                    searchSubject.onNext(SearchError(it.localizedMessage ?: "Server error"))
                }
            )
    }

}