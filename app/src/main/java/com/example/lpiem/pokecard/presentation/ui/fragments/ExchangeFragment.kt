package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.ExchangeServerResponse
import com.example.lpiem.pokecard.data.entity.Exchanges
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.data.entity.UserId
import com.example.lpiem.pokecard.presentation.presenter.ExchangeFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ExchangeView
import com.example.lpiem.pokecard.presentation.presenter.LoginFragmentPresenter
import com.example.lpiem.pokecard.presentation.ui.adapter.ExchangeRequestsAdapter
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_exchange_list.*
import kotlinx.android.synthetic.main.fragment_pokedex.*
import kotlinx.android.synthetic.main.fragment_pokeshop_buy.*
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ExchangeFragment : BaseFragment<ExchangeFragmentPresenter>(), ExchangeView {


    override val layoutId: Int = R.layout.fragment_exchange_list
    @Inject
    override lateinit var presenter: ExchangeFragmentPresenter
    var compositeDisposable = CompositeDisposable()

    override fun displayLoader() {
        //
    }

    override fun hideLoader() {
        //
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun sendExchangeRequest() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, ExchangeInitFragment())
            .commit()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showExchangeRequests(exchangeRequests: Exchanges) {
        Log.d("Datas : ", exchangeRequests.toString())
        val adapter = ExchangeRequestsAdapter(exchangeRequests,context!!, presenter, compositeDisposable)
        recyclerViewExchangeRequests.layoutManager = LinearLayoutManager(context)
        recyclerViewExchangeRequests.adapter = adapter
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userIdStr = sharedPreferences?.getString("user-id", "null")
        val userId = UserId(userIdStr!!)
        presenter.getExchangeRequestsForUser(userId!!, compositeDisposable)
        newExchangeButton.setOnClickListener {
            sendExchangeRequest()
        }
    }

}