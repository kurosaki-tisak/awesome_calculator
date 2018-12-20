package com.devtee.awesomecalculator

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.devtee.awesomecalculator.di.ApplicationComponent
import com.devtee.awesomecalculator.di.ContextModule
import com.devtee.awesomecalculator.di.DaggerApplicationComponent
import com.devtee.awesomecalculator.di.ServiceModule

open class AwesomeCalculatorApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .serviceModule(ServiceModule())
            .build()
    }
}