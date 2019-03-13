package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView

interface RegisterView : BaseView {

    fun displayLoader()
    fun hideLoader()
    fun showError(errorMessage: String)
    fun goToMain()
}