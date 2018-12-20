package com.devtee.awesomecalculator.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devtee.awesomecalculator.model.Currency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val api: CurrencyApi) {

    private val disposable = CompositeDisposable()
    private val data = MutableLiveData<List<Currency>>()
    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    fun fetchCurrencyRates() {
        loading.value = true

        disposable.add(
            api.getCurrencyRates().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableObserver<List<Currency>>() {

                    override fun onComplete() {
                        reset()
                    }

                    override fun onNext(value: List<Currency>) {
                        data.value = value
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                        loading.value = false
                    }
                })
        )
    }

    fun getLoading(): LiveData<Boolean> = loading
    fun getData(): LiveData<List<Currency>> = data
    fun getError(): LiveData<String> = error

    private fun reset() {
        loading.value = false
        error.value = null
        disposable.clear()
    }
}