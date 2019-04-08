package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.SecondResponse
import com.example.lpiem.pokecard.data.entity.UserId
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<ExchangeView>() {
    fun getExchangeRequestsForUser(userId : UserId,compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getExchangeRequestsForUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it.localizedMessage)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.showExchangeRequests(it)
                    }
                )
        )
    }

    fun secondrespondtoexchange(secondResponse: SecondResponse, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.secondrespondtoexchange(secondResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it.localizedMessage)
                    },
                    onSuccess = {
                        view.hideLoader()
                    }
                )
        )
    }

}