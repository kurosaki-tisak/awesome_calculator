package com.devtee.awesomecalculator.feature.numberpad

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NumberPadViewModel @Inject constructor() : ViewModel() {

    val currentValue = MutableLiveData<String>()
    val onItemPadClick = View.OnClickListener { itemPadClickListener(it) }

    private var prevValue = "0"

    init {
        currentValue.value = "0"
    }

    private fun itemPadClickListener(view: View) {
        when (view.tag) {
            "0" -> {
                if (prevValue == "0") return
                prevValue += view.tag
            }

            "." -> {
                if (prevValue.last() == '.') return
                if (prevValue.contains('.')) return
                prevValue += view.tag
            }

            "C" -> {
                prevValue = "0"
                currentValue.value = prevValue
            }

            else -> {
                if (prevValue == "0") prevValue.drop(1)
                prevValue += view.tag
            }
        }

        currentValue.value = prevValue
    }
}