package com.devtee.awesomecalculator.feature

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devtee.awesomecalculator.R
import com.devtee.awesomecalculator.feature.numberpad.NumberPadBinder
import com.devtee.awesomecalculator.databinding.ActivityCalculatorBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CalculatorActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCalculatorBinding>(this, R.layout.activity_calculator)

        val binder = CalculatorBinder(this, binding, viewModelFactory)
        binder.bindTo(this)

        val numberPadBinder = NumberPadBinder(this, binding.layoutNumberPad, viewModelFactory)
        numberPadBinder.bindTo(this)
    }

}