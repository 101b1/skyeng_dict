package com.ilih.skyengdict.di

import com.ilih.skyengdict.ui.router.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RouterModule(private val router: Router) {

    @Provides
    @MainScope
    fun provideRouter() = router

}