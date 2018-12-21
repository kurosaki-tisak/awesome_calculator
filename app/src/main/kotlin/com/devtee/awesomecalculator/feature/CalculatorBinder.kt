package com.devtee.awesomecalculator.feature

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory

class CalculatorBinder(activity: AppCompatActivity,
                       binding: ActivityCalculatorBinding,
                       viewModelFactory: ViewModelFactory) {

    private val view = CalculatorViewImpl(activity, binding)
    private val viewModel = ViewModelProviders.of(activity, viewModelFactory).get(CalculatorViewModel::class.java)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.currencyData.observe(owner, Observer { doNothing() })
        viewModel.subjectList.observe(owner, Observer { view.setupOptionsDialog(it!!.first, it.second) })
        viewModel.showOptionsDialog.observe(owner, Observer { view.showOptionsDialog(it) })
    }

    private fun doNothing() {}
}