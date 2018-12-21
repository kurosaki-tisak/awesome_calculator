package com.devtee.awesomecalculator.di

import android.app.Application
import com.devtee.awesomecalculator.AwesomeCalculatorApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [], modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    ContextModule::class,
    ServiceModule::class,
    ViewModelModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: AwesomeCalculatorApplication)
}