package com.devtee.awesomecalculator.feature.dialogpicker

import androidx.lifecycle.ViewModel
import java.io.Serializable

data class PickerItem(
    val id: Int,
    val title: String,
    var isSelected: Boolean = false
) : Serializable

class DialogPickerViewModel : ViewModel()