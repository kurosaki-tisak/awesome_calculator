package com.devtee.awesomecalculator.feature

import androidx.appcompat.app.AppCompatActivity
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding
import com.devtee.awesomecalculator.feature.dialogpicker.DialogPicker
import com.devtee.awesomecalculator.feature.dialogpicker.PickerItem

interface CalculatorView {
    fun setupOptionsDialog(default: String?, list: List<String>)
    fun showOptionsDialog(preselected: String?)
}

class CalculatorViewImpl(val activity: AppCompatActivity,
                         val binding: ActivityCalculatorBinding) : CalculatorView {

    private lateinit var dialog: DialogPicker

    override fun setupOptionsDialog(default: String?, list: List<String>) {
        val convertedList = list.mapIndexed { index, item ->
            PickerItem(
                    id = index,
                    title = item,
                    isSelected = default == item)
        }

        val fromSubmit: (Pair<Int, String>) -> Unit? = { binding.viewModel?.setSubmitSubject(it.second) }

        dialog = DialogPicker.newInstance(convertedList as ArrayList<PickerItem>, fromSubmit)
    }

    override fun showOptionsDialog(preselected: String?) {
        dialog.setupPreselectedItem(preselected)
        dialog.show(activity.supportFragmentManager, "")
    }
}