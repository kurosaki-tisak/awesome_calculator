package com.devtee.awesomecalculator.di

import com.devtee.awesomecalculator.repository.CurrencyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesRetrofit() = Retrofit
        .Builder()
        .baseUrl("https://my-json-server.typicode.com/kurosaki-tisak/currency_rate/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesCurrencyApi(retrofit: Retrofit) = retrofit.create(CurrencyApi::class.java)
}