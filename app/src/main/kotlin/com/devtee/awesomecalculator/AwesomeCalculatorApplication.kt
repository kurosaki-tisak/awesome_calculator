package com.devtee.awesomecalculator

import com.devtee.awesomecalculator.di.ApplicationComponent
import com.devtee.awesomecalculator.di.DaggerApplicationComponent
import com.devtee.awesomecalculator.di.applyAutoInjector
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class AwesomeCalculatorApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        val component: ApplicationComponent = DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component as AndroidInjector<DaggerApplication>
    }
}