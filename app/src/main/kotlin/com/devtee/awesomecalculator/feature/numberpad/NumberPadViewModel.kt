package com.devtee.awesomecalculator.feature.numberpad

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NumberPadViewModel @Inject constructor() : ViewModel() {

    val currentValue = MutableLiveData<String>()
    val onItemPadClick = View.OnClickListener { itemPadClickListener(it.tag) }

    private var prevValue = "0"

    init {
        currentValue.value = "0"
    }

    @VisibleForTesting
    fun itemPadClickListener(tag: Any) {
        when (tag) {
            "0" -> {
                if (prevValue == "0") return
                prevValue += tag
            }

            "." -> {
                if (prevValue.last() == '.') return
                if (prevValue.contains('.')) return
                prevValue += tag
            }

            "C" -> {
                prevValue = "0"
                currentValue.value = prevValue
            }

            else -> {
                if (prevValue == "0") prevValue = ""
                prevValue += tag
            }
        }

        currentValue.value = prevValue
    }

    @VisibleForTesting
    fun getData() = currentValue
}