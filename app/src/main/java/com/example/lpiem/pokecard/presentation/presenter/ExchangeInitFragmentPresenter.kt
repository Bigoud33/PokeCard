package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.ExchangeRequest
import com.example.lpiem.pokecard.data.network.PokeCardService
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeInitFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context): BasePresenter<ExchangeInitView>(), PokemonAdapter.ClickOnRecycler {

    fun start(compositeDisposable: CompositeDisposable) {
        view.displayLoader()
        val sharedPreferences = context.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getString("user-id", "null")
        getPokemonsForUser(userId!!, compositeDisposable)
    }

    fun getPokemonsForUser(userId: String, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getPokemonsForUser(userId)
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
        )
    }

    fun initExchange(userId: String, pokemonId: String, userIdTarget: String, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.initExchangeRequest(ExchangeRequest(userId, pokemonId, userIdTarget))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.executeResponse(it)
                    }
                )
        )
    }

    override fun itemClicked(pokemonId: String, context: Context) {
        view.updatePokemonSelected(pokemonId)
    }

}