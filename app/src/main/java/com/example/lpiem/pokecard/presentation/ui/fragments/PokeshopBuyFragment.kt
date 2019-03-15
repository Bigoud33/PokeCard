package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.Pokemon
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokeshopBuyView
import com.example.lpiem.pokecard.presentation.ui.adapter.BuyPokemonAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokeshop_buy.*
import javax.inject.Inject

class PokeshopBuyFragment : BaseFragment<PokeshopBuyFragmentPresenter>(), PokeshopBuyView {
    override fun displayLoader() {
        //
    }

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
        buyList.layoutManager = LinearLayoutManager(context)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)
        presenter.getPokemons()
    }

    override fun hideLoader() {
        //
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, "Error when getting pokemons : $errorMessage", Toast.LENGTH_SHORT).show()
    }

    override fun showPokemons(pokemons: Pokemons) {
        buyList.adapter = BuyPokemonAdapter(pokemons, context)
    }







}