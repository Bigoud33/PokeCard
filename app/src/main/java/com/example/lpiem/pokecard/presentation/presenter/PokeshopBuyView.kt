package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.Pokemons

interface PokeshopBuyView: BaseView {
    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun showPokemons(pokemons: Pokemons)
}