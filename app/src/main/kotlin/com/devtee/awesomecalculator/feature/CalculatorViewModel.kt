package com.devtee.awesomecalculator.feature

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class CalculatorViewModel : ViewModel() {

    val showingValue = ObservableField<String>()

    private var currentValue = ""

    init {

    }

    fun getNumberFromInput(number: String) {
        currentValue += number
        showingValue.set(currentValue)
    }
}