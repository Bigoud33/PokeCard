package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.presentation.presenter.PokedexFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokedexView
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_pokedex.*
import javax.inject.Inject

class PokedexFragment : BaseFragment<PokedexFragmentPresenter>(), PokedexView {

    override val layoutId: Int = R.layout.fragment_pokedex
    private val compositeDisposable = CompositeDisposable()

    @Inject
    override lateinit var presenter: PokedexFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        recyclerViewPokedex.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        presenter.start(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun displayLoader() {
        progress_bar.show()
    }

    override fun hideLoader() {
        progress_bar.hide()
    }

    override fun showPokemons(pokemonsList: Pokemons) {
        val adapter = PokemonAdapter(pokemonsList, presenter)
        recyclerViewPokedex.adapter = adapter
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    override fun goToDetail(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}