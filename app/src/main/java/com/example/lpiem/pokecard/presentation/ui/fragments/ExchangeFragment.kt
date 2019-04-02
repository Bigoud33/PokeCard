package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.Exchanges
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.presentation.presenter.ExchangeFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ExchangeView
import com.example.lpiem.pokecard.presentation.presenter.LoginFragmentPresenter
import com.example.lpiem.pokecard.presentation.ui.adapter.ExchangeRequestsAdapter
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_exchange_list.*
import kotlinx.android.synthetic.main.fragment_pokedex.*
import javax.inject.Inject

class ExchangeFragment : BaseFragment<ExchangeFragmentPresenter>(), ExchangeView {
    override val layoutId: Int = R.layout.fragment_exchange_list
    @Inject
    override lateinit var presenter: ExchangeFragmentPresenter

    override fun displayLoader() {
        //
    }

    override fun hideLoader() {
        //
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun sendExchangeRequest() {
        //
    }

    override fun showExchangeRequests(exchangeRequests: Exchanges) {
        val adapter = ExchangeRequestsAdapter(exchangeRequests, context)
        recyclerViewExchangeRequests.adapter = adapter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }

}