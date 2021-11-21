package com.ilih.skyengdict.di

import android.content.Context
import com.ilih.skyengdict.MainActivity
import com.ilih.skyengdict.ui.router.Router
import com.ilih.skyengdict.ui.search.SearchFragment
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(dependencies = [AppComponent::class], modules = [ContextModule::class, RouterModule::class])
@MainScope
interface MainComponent {

    companion object {
        fun create(appComponent: AppComponent, context: Context, router: Router): MainComponent {
            return DaggerMainComponent.builder()
                .appComponent(appComponent)
                .contextModule(ContextModule(context))
                .routerModule(RouterModule(router))
                .build()
        }
    }

    fun getContext(): Context

    fun getRouter(): Router

    fun inject(mainActivity: MainActivity)

    fun injectSearchFragment(searchFragment: SearchFragment)
}