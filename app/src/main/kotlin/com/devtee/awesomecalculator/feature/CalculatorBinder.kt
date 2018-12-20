package com.devtee.awesomecalculator.feature

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.app.AppCompatActivity
import com.devtee.awesomecalculator.common.numberpad.NumberPadViewModel
import com.devtee.awesomecalculator.common.viewModel
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding

class CalculatorBinder(activity: AppCompatActivity,
                       binding: ActivityCalculatorBinding) {

    val viewModel = activity.viewModel { CalculatorViewModel() }
    private val numberPadViewModel = activity.viewModel { NumberPadViewModel() }

    init {
        binding.viewModel = viewModel

        numberPadViewModel.onChange = { viewModel.getNumberFromInput(it)}
    }

    fun bindTo(owner: LifecycleOwner) {

    }
}