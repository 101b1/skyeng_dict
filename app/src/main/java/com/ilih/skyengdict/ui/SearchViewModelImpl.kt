package com.ilih.skyengdict.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.skyengdict.domain.dto.SearchError
import com.ilih.skyengdict.domain.dto.SearchResult
import com.ilih.skyengdict.domain.dto.SearchResultDto
import com.ilih.skyengdict.domain.dto.SearchSuccess
import com.ilih.skyengdict.domain.interactor.SearchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModelImpl
@Inject
constructor(private val interactor: SearchInteractor): ViewModel(), SearchViewModel, SearchView.Listener {

    private val _data = MutableLiveData<List<SearchResultDto>>()
    val data = _data

    private val _state = MutableLiveData<SearchResult>()
    val state = _state

    private val dataList = mutableListOf<SearchResultDto>()
    private var currentQuery: String = ""
    private var pagesDownloaded = 0
    private var hasMorePages = true

    private val disposable: Disposable

    init {
        disposable = interactor.getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                    is SearchSuccess ->{
                        if (it.results.isEmpty()){
                            hasMorePages = false
                        } else {
                            pagesDownloaded +=1
                            dataList.addAll(it.results)
                            _data.value = dataList
                            _state.value = it
                        }
                    }
                    is SearchError ->{
                        _state.value = it
                    }
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    override fun getListener(): SearchView.Listener {
        return this
    }

    override fun getData(): LiveData<List<SearchResultDto>> {
        return data
    }

    override fun getState(): LiveData<SearchResult> {
        return state
    }

    override fun search(query: String) {
        if (hasMorePages){
            var pageNumber = 1
            if (query == currentQuery){
                pageNumber = pagesDownloaded + 1
            } else{
                currentQuery = query
            }
            interactor.searchWord(query, pageNumber)
        }
    }
}