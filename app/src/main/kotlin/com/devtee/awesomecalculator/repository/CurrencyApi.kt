package com.devtee.awesomecalculator.repository

import com.devtee.awesomecalculator.model.Currency
import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyApi {

    @GET("currency")
    fun getCurrencyRates(): Observable<List<Currency>>
}