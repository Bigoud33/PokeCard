package com.example.lpiem.pokecard.presentation.ui.fragments

import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.PokedexFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokedexView
import javax.inject.Inject

class PokedexFragment : BaseFragment<PokedexFragmentPresenter>(), PokedexView {

    override val layoutId: Int = R.layout.fragment_pokedex

    @Inject
    override lateinit var presenter: PokedexFragmentPresenter

}