package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.domain.MainPokemonModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokedexFragmentPresenter
@Inject constructor(private val model: MainPokemonModel): BasePresenter<PokedexFragmentPresenter.PokedexFragmentView>() {

    fun start() {
        view.displayLoader()
        getPokemons()
    }

    fun getPokemons() {
        model.getPokemons()
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


    interface PokedexFragmentView : BaseView {

    }
}