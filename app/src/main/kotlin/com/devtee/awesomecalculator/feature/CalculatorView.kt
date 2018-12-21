package com.devtee.awesomecalculator.feature

import androidx.appcompat.app.AppCompatActivity
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding
import com.devtee.awesomecalculator.feature.dialogpicker.DialogPicker
import com.devtee.awesomecalculator.feature.dialogpicker.PickerItem

interface CalculatorView {
    fun setupOptionsDialog(default: String?, list: List<PickerItem>)
    fun showOptionsDialog(value: Pair<String?, ClickEvent>)
}

class CalculatorViewImpl(val activity: AppCompatActivity,
                         val binding: ActivityCalculatorBinding) : CalculatorView {

    private lateinit var dialog: DialogPicker
    private lateinit var event: ClickEvent

    override fun setupOptionsDialog(default: String?, list: List<PickerItem>) {
        val fromSubmit: (Pair<Int, PickerItem>) -> Unit? =
                { binding.viewModel?.setSubmitSubject(Pair(it.second, event)) }
        dialog = DialogPicker.newInstance(list as ArrayList<PickerItem>, fromSubmit)
        dialog.setupPreselectedItem(default)
    }

    override fun showOptionsDialog(value: Pair<String?, ClickEvent>) {
        val (preselected, event) = Pair(value.first, value.second)

        this.event = event

        dialog.setupPreselectedItem(preselected)
        dialog.show(activity.supportFragmentManager, event.name)
    }
}