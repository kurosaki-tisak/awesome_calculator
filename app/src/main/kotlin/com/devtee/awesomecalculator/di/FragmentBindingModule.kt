package com.devtee.awesomecalculator.di

import com.devtee.awesomecalculator.feature.dialogpicker.DialogPicker
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    internal abstract fun contributeDialogPicker(): DialogPicker
}