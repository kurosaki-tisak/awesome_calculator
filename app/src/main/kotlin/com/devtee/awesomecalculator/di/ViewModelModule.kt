package com.devtee.awesomecalculator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devtee.awesomecalculator.common.numberpad.NumberPadViewModel
import com.devtee.awesomecalculator.di.utils.ViewModelFactory
import com.devtee.awesomecalculator.di.utils.ViewModelKey
import com.devtee.awesomecalculator.feature.CalculatorViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NumberPadViewModel::class)
    abstract fun bindNumberPadViewModel(viewModel: NumberPadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalculatorViewModel::class)
    abstract fun bindCalculatorViewModel(viewModel: CalculatorViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}