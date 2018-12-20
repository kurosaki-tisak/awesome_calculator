package com.devtee.awesomecalculator.common.numberpad

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import com.devtee.awesomecalculator.common.onValueChange
import com.devtee.awesomecalculator.service.ResourceService
import javax.inject.Inject

class NumberPadViewModel : ViewModel() {

    @Inject
    lateinit var resourceService: ResourceService

    private val currentValue = ObservableField<String>()
    val onItemPadClick = View.OnClickListener { itemPadClickListener(it) }

    var onChange: ((Pair<String, String>) -> Unit)? = null

    init {
        currentValue.onValueChange {

        }
    }

    private fun itemPadClickListener(view: View) {
        currentValue.set(view.tag.toString())
    }
}