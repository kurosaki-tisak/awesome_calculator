package com.devtee.awesomecalculator.feature

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devtee.awesomecalculator.R
import com.devtee.awesomecalculator.common.numberpad.NumberPadBinder
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCalculatorBinding>(this, R.layout.activity_calculator)

        val binder = CalculatorBinder(this, binding)
        binder.bindTo(this)

        val numberPadBinder = NumberPadBinder(this, binding.layoutNumberPad)
        numberPadBinder.bindTo(this)
    }

}