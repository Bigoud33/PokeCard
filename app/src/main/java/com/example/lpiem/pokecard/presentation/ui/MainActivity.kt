package com.example.lpiem.pokecard.presentation.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.presentation.presenter.MainActivityPresenter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
    }
}
