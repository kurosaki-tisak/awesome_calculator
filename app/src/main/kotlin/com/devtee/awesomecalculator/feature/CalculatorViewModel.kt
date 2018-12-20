package com.devtee.awesomecalculator.feature

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devtee.awesomecalculator.common.filter
import com.devtee.awesomecalculator.common.map
import com.devtee.awesomecalculator.common.mapNotNull
import com.devtee.awesomecalculator.common.numberpad.NumberPadViewModel
import com.devtee.awesomecalculator.model.Currency
import com.devtee.awesomecalculator.repository.CurrencyRepository
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(
    private val numberPadViewModel: NumberPadViewModel,
    private val repository: CurrencyRepository
) : ViewModel() {

    val showingValue = ObservableField<String>()
    val loading = ObservableBoolean(true)

    val currencyData: LiveData<List<Currency>>
        get() = repository.getData().map { value ->

            return@map value
        }

    val currentValue: LiveData<String>
        get() = numberPadViewModel.currentValue.filter {
            showingValue.set(it)
            true
        }

    init {
        fetchCurrencyData()
    }

    private fun fetchCurrencyData() {
        repository.fetchCurrencyRates()
    }

    fun isLoading() = repository.getLoading().mapNotNull { value ->
        when (value) {
            true -> loading.set(true)
            else -> loading.set(false)
        }
    }

    fun error(): LiveData<String> = repository.getError().mapNotNull { error ->
        return@mapNotNull error
    }
}