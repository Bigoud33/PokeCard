package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.domain.MainPokemonModel
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import javax.inject.Inject

class MainActivityPresenter
@Inject constructor(private val model: MainPokemonModel, private val activity: MainActivity): BasePresenter<MainActivityPresenter.MainActivityView>() {


    interface MainActivityView: BaseView {

    }
}

