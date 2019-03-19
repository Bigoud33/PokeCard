package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.SigninUser
import com.example.lpiem.pokecard.data.entity.Token
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginFragmentPresenter
@Inject
constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<LoginView>() {
    fun signin(signinUser: SigninUser){
        service.signin(signinUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    /*view.hideLoader()
                    view.showError(it)*/
                    view.showError(it.localizedMessage)
                },
                onSuccess = {
                    /*view.hideLoader()
                    view.showPokemons(it)*/
                    if(it.error != null){
                        view.showError(it.error)
                    } else {
                        val sharedPreferences = context.getSharedPreferences("pokecard", Context.MODE_PRIVATE)

                        sharedPreferences
                            .edit()
                            .putString("user-token", it.token)
                            .putString("user-id", it.id)
                            .apply()
                        view.goToMain()

                    }
                }
            )
    }

    fun signinToken(signinToken: Token) {
        service.signinToken(signinToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    view.showError(it.localizedMessage)
                },
                onSuccess = {
                    if (it.error != null) {
                        view.showError(it.error)
                    } else {
                        val sharedPreferences = context.getSharedPreferences("pokecard", Context.MODE_PRIVATE)

                        sharedPreferences
                            .edit()
                            .putString("user-token", it.token)
                            .putString("user-id", it.id)
                            .apply()
                        view.goToMain()
                    }
                }
            )
    }
}