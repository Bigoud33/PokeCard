package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonDetailsFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<PokemonDetailsView>(){

    fun start(pokemonId: String, compositeDisposable: CompositeDisposable) {
        view.displayLoader()
        getPokemon(pokemonId, compositeDisposable)
    }

    private fun getPokemon(pokemonId: String, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getPokemon(pokemonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.showPokemon(it)
                    }
                )
        )
    }

}