package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.PokemonDetails

interface PokemonDetailsView : BaseView {

    fun displayLoader()
    fun hideLoader()
    fun showPokemon(pokemon: PokemonDetails)
    fun showError(throwable: Throwable)

}