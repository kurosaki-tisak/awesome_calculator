package com.devtee.awesomecalculator.feature

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devtee.awesomecalculator.common.SingleLiveEvent
import com.devtee.awesomecalculator.common.map
import com.devtee.awesomecalculator.common.mapNotNull
import com.devtee.awesomecalculator.common.onValueChange
import com.devtee.awesomecalculator.feature.dialogpicker.PickerItem
import com.devtee.awesomecalculator.repository.CurrencyRepository
import java.text.DecimalFormat
import javax.inject.Inject

enum class ClickEvent {
    ORIGINAL_CLICK,
    CONVERTER_CLICK
}

class CalculatorViewModel @Inject constructor(private val repository: CurrencyRepository) : ViewModel() {

    val showingValue = ObservableField<String>()
    val originalValue = ObservableField<String>("THB")
    val convertedValue = ObservableField<String>("USD")
    val outputValue = ObservableField<String>()
    val isLoading = ObservableBoolean(true)

    val originalClick = View.OnClickListener {
        showOptionsDialog.value = Pair(originalValue.get(), ClickEvent.ORIGINAL_CLICK)
    }
    val convertClick = View.OnClickListener {
        showOptionsDialog.value = Pair(convertedValue.get(), ClickEvent.CONVERTER_CLICK)
    }
    val convertButtonClick = View.OnClickListener { convertCurrency() }

    val currencyData: LiveData<List<PickerItem>>
        get() = repository.getData().map {
            val result = it.mapIndexed { index, it ->
                return@mapIndexed PickerItem(
                        id = index,
                        title = it.title,
                        rate = it.rate,
                        isSelected = false
                )
            }
            data = result
            return@map result
        }

    val loadingLiveData: LiveData<Unit>
        get() = repository.getLoading().mapNotNull { value ->
            when (value) {
                true -> isLoading.set(true)
                else -> isLoading.set(false)
            }
        }

    val showOptionsDialog = SingleLiveEvent<Pair<String?, ClickEvent>>()

    private var data: List<PickerItem>? = null
    private var original: PickerItem? = null
    private var converter: PickerItem? = null

    init {
        fetchCurrencyData()

        originalValue.onValueChange { convertCurrency() }
        convertedValue.onValueChange { convertCurrency() }
    }

    private fun fetchCurrencyData() {
        repository.fetchCurrencyRates()
    }

    private fun convertCurrency() {
        original = findMatchCurrency(originalValue.get()!!)
        converter = findMatchCurrency(convertedValue.get()!!)

        val currencyFormat = DecimalFormat("00.00")
        val inputValue = showingValue.get()!!.toDouble()
        val localConversion = original!!.rate
        val startConversion = converter!!.rate

        val amount = inputValue * (startConversion!!.div(localConversion!!))

        outputValue.set(currencyFormat.format(amount) + " ${converter!!.title?.toUpperCase()}")
    }

    private fun findMatchCurrency(value: String): PickerItem? {
        return data?.find { it.title == value }
    }

    fun error(): LiveData<String> = repository.getError().mapNotNull { error ->
        return@mapNotNull error
    }

    fun setSubmitSubject(click: Pair<PickerItem?, ClickEvent>) {
        val (item, event) = Pair(click.first, click.second)
        when (event) {
            ClickEvent.ORIGINAL_CLICK -> {
                originalValue.set(item?.title)
            }
            ClickEvent.CONVERTER_CLICK -> {
                convertedValue.set(item?.title)
            }
        }
    }
}