package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.Pokemons

interface PokedexView : BaseView {

    fun displayLoader()
    fun hideLoader()
    fun showPokemons(pokemonsList: Pokemons)
    fun showError(throwable: Throwable)
    fun goToDetail(position: Int)

}