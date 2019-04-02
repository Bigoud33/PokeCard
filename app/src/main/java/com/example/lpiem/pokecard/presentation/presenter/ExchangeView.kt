package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.Exchange
import com.example.lpiem.pokecard.data.entity.Exchanges

interface ExchangeView : BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun sendExchangeRequest()
    fun showExchangeRequests(exchangeRequests: Exchanges)
}