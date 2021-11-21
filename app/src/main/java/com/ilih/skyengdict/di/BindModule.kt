package com.ilih.skyengdict.di

import com.ilih.skyengdict.domain.interactor.SearchInteractor
import com.ilih.skyengdict.domain.interactor.SearchInteractorImpl
import com.ilih.skyengdict.ui.search.SearchViewModel
import com.ilih.skyengdict.ui.search.SearchViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class BindModule {

    @Binds
    @Reusable
    abstract fun bindsSearchViewModel(viewModel: SearchViewModelImpl): SearchViewModel

    @Binds
    @Reusable
    abstract fun bindsSearchInteractor(interactorImpl: SearchInteractorImpl): SearchInteractor

}