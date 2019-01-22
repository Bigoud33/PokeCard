package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.domain.MainPokemonModel
import com.example.lpiem.pokecard.presentation.ui.activities.LoginActivity
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import javax.inject.Inject

class LoginActivityPresenter
@Inject constructor(private val model: MainPokemonModel, private val activity: LoginActivity): BasePresenter<LoginActivityPresenter.LoginActivityView>() {


    interface LoginActivityView: BaseView {

    }
}