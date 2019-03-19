package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokeshopBuyFragmentPresenter
@Inject
constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<PokeshopBuyView>() {
    fun getPokemons(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it.localizedMessage)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.showPokemons(it)
                    }
                )
        )

    }
}
