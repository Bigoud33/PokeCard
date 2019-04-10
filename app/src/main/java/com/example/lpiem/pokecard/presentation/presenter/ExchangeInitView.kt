package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.ExchangeServerResponse
import com.example.lpiem.pokecard.data.entity.Pokemons

interface ExchangeInitView : BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showPokemons(pokemonsList: Pokemons)
    fun showError(errorMessage: Throwable)
    fun updatePokemonSelected(pokemonId: String)
    fun executeResponse(exchangeServerResponse: ExchangeServerResponse)
}