package com.devtee.awesomecalculator.di

import android.content.Context
import com.devtee.awesomecalculator.service.ResourceService
import com.devtee.awesomecalculator.service.ResourceServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ServiceModule {

    @Singleton
    @Provides
    open fun providesResourceService(context: Context): ResourceService = ResourceServiceImpl(context)

}