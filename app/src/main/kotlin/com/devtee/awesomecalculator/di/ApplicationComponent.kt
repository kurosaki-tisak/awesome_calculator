package com.devtee.awesomecalculator.di

import com.devtee.awesomecalculator.service.ResourceService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, ServiceModule::class ])
interface ApplicationComponent {

    fun getResourceService(): ResourceService
}