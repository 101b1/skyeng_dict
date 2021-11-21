package com.ilih.skyengdict.di

import com.ilih.skyengdict.App
import com.ilih.skyengdict.domain.interactor.SearchInteractor
import com.ilih.skyengdict.ui.search.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [BindModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {

    companion object {
        fun create(): AppComponent {
            return DaggerAppComponent.builder().build()
        }
    }

    fun getSearchViewModel(): SearchViewModel

    fun getSearchInteractor(): SearchInteractor

    fun inject(app: App)

}