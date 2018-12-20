package com.devtee.awesomecalculator.di

import com.devtee.awesomecalculator.feature.CalculatorActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCalculatorActivity(): CalculatorActivity
}