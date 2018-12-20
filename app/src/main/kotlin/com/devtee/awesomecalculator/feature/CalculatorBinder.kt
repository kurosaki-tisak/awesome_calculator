package com.devtee.awesomecalculator.feature

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory

class CalculatorBinder(
    activity: AppCompatActivity,
    binding: ActivityCalculatorBinding,
    viewModelFactory: ViewModelFactory
) {

    private val viewModel = ViewModelProviders.of(activity, viewModelFactory).get(CalculatorViewModel::class.java)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.currentValue.observe(owner, Observer { doNothing() })
        viewModel.currencyData.observe(owner, Observer { doNothing() })
    }

    private fun doNothing() {}
}