package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PokeshopBuyFragment : BaseFragment<PokeshopBuyFragmentPresenter>(), PokeshopBuyView {
    override val layoutId: Int = R.layout.fragment_pokeshop_buy

    @Inject
    override lateinit var presenter: PokeshopBuyFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

    }


}