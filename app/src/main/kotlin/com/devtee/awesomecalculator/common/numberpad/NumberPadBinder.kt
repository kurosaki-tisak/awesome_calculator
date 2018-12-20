package com.devtee.awesomecalculator.common.numberpad

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.app.AppCompatActivity
import com.devtee.awesomecalculator.common.viewModel
import com.devtee.awesomecalculator.databinding.PartialNumberPadBinding

class NumberPadBinder(activity: AppCompatActivity,
                      binding: PartialNumberPadBinding) {

     private val viewModel = activity.viewModel { NumberPadViewModel() }

    init {
        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {

    }
}