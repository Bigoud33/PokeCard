package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.PokeshopFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokeshopView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokeshop.*
import javax.inject.Inject

class PokeshopFragment : BaseFragment<PokeshopFragmentPresenter>(), PokeshopView {

    override val layoutId: Int = R.layout.fragment_pokeshop

    @Inject
    override lateinit var presenter: PokeshopFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

        buyButton.setOnClickListener {
            showBuyFragment()
        }
    }

    fun showBuyFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, PokeshopBuyFragment())
            .commit()
    }
}