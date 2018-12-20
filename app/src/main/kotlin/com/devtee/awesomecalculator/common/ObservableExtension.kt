package com.devtee.awesomecalculator.common

import androidx.databinding.Observable
import androidx.databinding.ObservableField

fun <T> ObservableField<T>.onValueChange(callback: (T) -> Unit) {
    this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(p0: Observable?, p1: Int) {
            this@onValueChange.get()?.let { callback.invoke(it) }
        }
    })
}