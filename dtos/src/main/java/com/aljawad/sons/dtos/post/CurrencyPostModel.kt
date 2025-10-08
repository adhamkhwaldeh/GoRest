package com.aljawad.sons.dtos.post

import androidx.databinding.BaseObservable

data class CurrencyPostModel(
    var firstCurrency: String,
    var secondCurrency: String,
    var rate: String
) : BaseObservable() {


}
