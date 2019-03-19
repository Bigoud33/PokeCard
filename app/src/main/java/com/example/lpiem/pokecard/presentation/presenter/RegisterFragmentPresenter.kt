package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.SignupUser
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterFragmentPresenter
@Inject
constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<RegisterView>() {
    fun signup(signupUser: SignupUser) {
        service.signup(signupUser)
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
}
