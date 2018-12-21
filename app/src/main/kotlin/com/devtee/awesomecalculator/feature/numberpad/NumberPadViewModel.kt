package com.devtee.awesomecalculator.feature.numberpad

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.devtee.awesomecalculator.common.onValueChange
import javax.inject.Inject

class NumberPadViewModel @Inject constructor() : ViewModel() {

    val currentValue = ObservableField<String>()
    val onItemPadClick = View.OnClickListener { itemPadClickListener(it) }

    var onChange: (() -> Unit)? = null

    private var prevValue = "0"

    init {
        currentValue.set("0")

        currentValue.onValueChange {
            onChange?.invoke()
        }
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
                currentValue.set(prevValue)
            }

            else -> {
                if (prevValue == "0") prevValue.drop(1)
                prevValue += view.tag
            }
        }

        currentValue.set(prevValue)
    }
}