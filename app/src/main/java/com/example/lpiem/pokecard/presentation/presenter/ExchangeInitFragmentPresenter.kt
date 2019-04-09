package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.network.PokeCardService
import javax.inject.Inject

class ExchangeInitFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context): BasePresenter<ExchangeInitView>() {

}