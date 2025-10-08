package com.aljawad.sons.goRestCore.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.aljawad.sons.dtos.post.CurrencyPostModel
import com.aljawad.sons.mainlibrary.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CurrencyExchangeViewModel(
    // UseCase
    private val handle: SavedStateHandle
) : BaseViewModel() {

    var postModel: CurrencyPostModel = CurrencyPostModel("", "", "")

    val currencyResult: MutableLiveData<Double> = MutableLiveData()

    fun exchange(postModel: CurrencyPostModel) {
        // Use UseCase
        val res = postModel.firstCurrency.toDouble() * postModel.rate.toDouble()
        currencyResult.value = res
    }

}