package com.example.lpiem.pokecard.presentation.presenter

import com.example.lpiem.pokecard.base.BaseView
import com.example.lpiem.pokecard.data.entity.User

interface ProfileView : BaseView {
    fun showError(errorMessage : String)
    fun showProfile(user: User)
}