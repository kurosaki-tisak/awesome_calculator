package com.devtee.awesomecalculator.common.numberpad

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.devtee.awesomecalculator.databinding.PartialNumberPadBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory

class NumberPadBinder(
    activity: AppCompatActivity,
    binding: PartialNumberPadBinding,
    viewModelFactory: ViewModelFactory
) {

    private val viewModel = ViewModelProviders.of(activity, viewModelFactory).get(NumberPadViewModel::class.java)

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.currentValue.observe(owner, Observer { doNothing() })
    }

    private fun doNothing() {}
}