package com.ilih.skyengdict

import android.app.Application
import com.ilih.skyengdict.di.AppComponent
import io.reactivex.plugins.RxJavaPlugins

open class App: Application() {

    companion object{
        private var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { e -> {} }
//        getAppComponent().inject(this)
    }

    open fun getAppComponent(): AppComponent{
        return appComponent ?: AppComponent.create().also{
            appComponent = it
        }
    }
}