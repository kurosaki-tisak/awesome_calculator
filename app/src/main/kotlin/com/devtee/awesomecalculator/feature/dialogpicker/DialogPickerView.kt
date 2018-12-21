package com.devtee.awesomecalculator.feature.dialogpicker

import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtee.awesomecalculator.databinding.FragmentDialogPickerBinding

interface DialogPickerView {
    fun dismissDialog()
    fun setItems(items: List<PickerItem>?)
}

class DialogPickerViewImpl(val fragment: DialogFragment,
                           val binding: FragmentDialogPickerBinding,
                           itemClicked: (PickerItem?) -> Unit) : DialogPickerView {

    private val listAdapter = DialogPickerListAdapter(itemClicked)

    init {
        with(binding.recyclerView) {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(fragment.context)
            setHasFixedSize(true)
        }
    }

    override fun dismissDialog() {
        fragment.dismiss()
    }

    override fun setItems(items: List<PickerItem>?) {
        (binding.recyclerView.adapter as DialogPickerListAdapter).items = items ?: emptyList()
    }
}
