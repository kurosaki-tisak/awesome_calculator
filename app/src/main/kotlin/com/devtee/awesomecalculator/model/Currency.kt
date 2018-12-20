package com.devtee.awesomecalculator.model

import com.google.gson.annotations.SerializedName

data class Currency(

	@field:SerializedName("rate")
	val rate: Double? = null,

	@field:SerializedName("title")
	val title: String? = null
)