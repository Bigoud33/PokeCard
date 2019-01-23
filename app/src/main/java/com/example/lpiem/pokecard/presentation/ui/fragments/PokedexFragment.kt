package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.PokedexFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokedexView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PokedexFragment : BaseFragment<PokedexFragmentPresenter>(), PokedexView {

    override val layoutId: Int = R.layout.fragment_pokedex

    @Inject
    override lateinit var presenter: PokedexFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}