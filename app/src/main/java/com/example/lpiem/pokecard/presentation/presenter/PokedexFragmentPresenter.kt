package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.Pokemon
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.domain.MainPokemonModel
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokedexFragmentPresenter
@Inject constructor(private val model: MainPokemonModel): BasePresenter<PokedexView>(), PokemonAdapter.ClickOnRecycler {

    override fun itemClicked(position: Int, context: Context) {
        view.goToDetail(position)
    }

    fun start() {
        view.displayLoader()
        getPokemons()
    }

    fun getPokemons() {
        model.getPokemons()
            .flatMap {
                model.getPokemons()



                return@flatMap Single.just(Pokemons(emptyList<Pokemon>()))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    view.hideLoader()
                    view.showError(it)
                },
                onSuccess = {
                    view.hideLoader()
                    view.showPokemons(it)
                }
            )
    }
}