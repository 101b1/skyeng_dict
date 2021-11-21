package com.ilih.skyengdict.di

import com.ilih.skyengdict.data.SearchService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun provideClient() =
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideClient())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideSearchService(): SearchService{
        return provideRetrofit().create(SearchService::class.java)
    }

}