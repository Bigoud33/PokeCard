package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.ExchangeInitFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ExchangeInitView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_exchange_init.*
import javax.inject.Inject

class ExchangeInitFragment : BaseFragment<ExchangeInitFragmentPresenter>(), ExchangeInitView {

    override val layoutId: Int = R.layout.fragment_exchange_init
    @Inject
    override lateinit var presenter: ExchangeInitFragmentPresenter
    var compositeDisposable = CompositeDisposable()
    var pokemonId: String? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userIdStr = sharedPreferences?.getString("user-id", "null")
        exchangeInitButton.setOnClickListener {
            if (userIdTextEdit.text!!.isNotBlank() && pokemonId != null) {

            }
        }
    }

    override fun displayLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}