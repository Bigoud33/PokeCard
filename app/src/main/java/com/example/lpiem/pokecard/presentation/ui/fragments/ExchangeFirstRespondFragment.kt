package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.FirstResponse
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.data.entity.UserId
import com.example.lpiem.pokecard.presentation.presenter.ExchangeFirstRespondFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ExchangeFirstRespondView
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_exchange_first_respond.*
import javax.inject.Inject

class ExchangeFirstRespondFragment : BaseFragment<ExchangeFirstRespondFragmentPresenter>(), ExchangeFirstRespondView {
    override fun showError2(errorStr: String) {
        Toast.makeText(context, errorStr, Toast.LENGTH_SHORT).show()
    }

    override fun goNext(pokemonId: String) {
        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userIdStr = sharedPreferences?.getString("user-id", "null")
        var bundle = this.arguments
        val exchangeId = bundle!!.getString("exchangeId")

        val firstResponse = FirstResponse(exchangeId, userIdStr!! , pokemonId)
        presenter.respondToExchange(firstResponse, compositeDisposable)
    }

    override fun displayLoader() {
        //
    }

    override fun hideLoader() {
        //
    }



    override fun showPokemons(pokemonsList: Pokemons) {
        Log.d("TEST",pokemonsList.toString())
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = PokemonAdapter(pokemonsList, presenter)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_SHORT).show()
    }

    override val layoutId: Int = R.layout.fragment_exchange_first_respond
    @Inject
    override lateinit var presenter: ExchangeFirstRespondFragmentPresenter
    var compositeDisposable = CompositeDisposable()

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
        presenter.getPokemonsForUser(userIdStr, compositeDisposable)

    }
}