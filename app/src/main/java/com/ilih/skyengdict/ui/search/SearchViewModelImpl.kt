package com.ilih.skyengdict.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilih.skyengdict.domain.dto.SearchError
import com.ilih.skyengdict.domain.dto.SearchResult
import com.ilih.skyengdict.domain.dto.SearchResultDto
import com.ilih.skyengdict.domain.dto.SearchSuccess
import com.ilih.skyengdict.domain.interactor.SearchInteractorImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModelImpl
@Inject
constructor(private val interactor: SearchInteractorImpl): ViewModel(), SearchViewModel,
    SearchView.Listener {

    private val _data = MutableLiveData<List<SearchResultDto>>()
    val data = _data

    private val _state = MutableLiveData<SearchState>()
    val state = _state

    private val dataList = mutableListOf<SearchResultDto>()
    private var currentQuery: String = ""
    private var pagesDownloaded = 0
    private var hasMorePages = true

    private val disposable: Disposable

    init {
        _data.value = dataList
        disposable = interactor.getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                    is SearchSuccess ->{
                        if (it.results.isEmpty()){
                            if (dataList.isEmpty()){
                                _state.value = SearchNoResults
                            }
                            else{
                                hasMorePages = false
                                _state.value = SearchSuccess
                            }
                        } else {
                            pagesDownloaded +=1
                            dataList.addAll(it.results)
                            _data.value = dataList
                            _state.value = SearchSuccess
                        }
                    }
                    is SearchError ->{
                        _state.value = com.ilih.skyengdict.ui.search.SearchError(it.error)
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

    override fun getState(): LiveData<SearchState> {
        return state
    }

    override fun search(query: String) {
        if(currentQuery != query){
            currentQuery = query
            dataList.clear()
            hasMorePages = true
            pagesDownloaded = 0
        }
        if (hasMorePages){
            _state.value = SearchLoading
            interactor.searchWord(query, pagesDownloaded + 1)
        }
    }
}