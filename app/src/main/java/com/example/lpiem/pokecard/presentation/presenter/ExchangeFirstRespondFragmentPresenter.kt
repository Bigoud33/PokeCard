package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.network.PokeCardService
import com.example.lpiem.pokecard.presentation.ui.adapter.ExchangeRequestsAdapter
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeFirstRespondFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<ExchangeFirstRespondView>(), PokemonAdapter.ClickOnRecycler{

    override fun itemClicked(pokemonId: String, context: Context) {

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
}