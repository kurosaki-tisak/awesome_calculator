package com.devtee.awesomecalculator.feature.dialogpicker

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Serializable
import javax.inject.Inject

data class PickerItem(
        val id: Int,
        val title: String?,
        val rate: Double?,
        var isSelected: Boolean = false
) : Serializable

class DialogPickerViewModel @Inject constructor() : ViewModel() {

    val onClickDismiss = View.OnClickListener { clickDismiss() }

    val isDismissDialog = MutableLiveData<Boolean?>()
    val submit = MutableLiveData<Pair<Int, PickerItem>>()
    val listItemsLiveData = MutableLiveData<List<PickerItem>>()

    fun setupBoxList(list: List<PickerItem>) {
        listItemsLiveData.value = list
    }

    fun setupPreselectedItem(preselected: String?) {
        val items = listItemsLiveData.value?.toMutableList()
        items?.forEach { it.isSelected = (it.title == preselected) }

        listItemsLiveData.value = items
    }

    fun onSubmit(item: PickerItem) {
        submit.value = Pair(item.id, item)

        isDismissDialog.value = true
    }

    private fun clickDismiss(): Boolean {
        isDismissDialog.value = true
        return true
    }
}