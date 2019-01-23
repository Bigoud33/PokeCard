package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.domain.MainPokemonModel
import com.example.lpiem.pokecard.presentation.ui.fragments.PokedexFragment
import javax.inject.Inject

class PokedexFragmentPresenter
@Inject constructor(private val model: MainPokemonModel, private val fragment: PokedexFragment): BasePresenter<PokedexFragmentPresenter.PokedexFragmentView>() {




    interface PokedexFragmentView : BaseView {

    }
}