package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.MainFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.MainView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : BaseFragment<MainFragmentPresenter>(), MainView {

    override val layoutId: Int = R.layout.fragment_main

    @Inject
    override lateinit var presenter: MainFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
    }
}