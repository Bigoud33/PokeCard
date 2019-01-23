package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.Pokemon

interface PokedexView : BaseView {

    fun displayLoader()
    fun hideLoader()
    fun showPokemons(pokemons: List<Pokemon>)
}