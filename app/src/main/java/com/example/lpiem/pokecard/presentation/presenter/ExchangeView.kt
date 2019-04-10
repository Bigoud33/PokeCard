package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.ExchangeServerResponse
import com.example.lpiem.pokecard.data.entity.Exchanges
import com.example.lpiem.pokecard.data.entity.SecondResponse

interface ExchangeView : BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun sendExchangeRequest()
    fun showExchangeRequests(exchangeRequests: Exchanges)
    fun showExchangeFirstRespondFragment(exchangeId : String)
}