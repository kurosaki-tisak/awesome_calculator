package com.devtee.awesomecalculator.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("show")
fun showOrGoneView(view: View, show: Boolean) {
    when (show) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}