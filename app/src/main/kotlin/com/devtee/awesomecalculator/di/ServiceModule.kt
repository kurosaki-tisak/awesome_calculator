package com.devtee.awesomecalculator.di

import android.content.Context
import com.devtee.awesomecalculator.service.ResourceService
import com.devtee.awesomecalculator.service.ResourceServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideResourceService(context: Context): ResourceService = ResourceServiceImpl(context)
}