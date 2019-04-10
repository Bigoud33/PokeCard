package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.ExchangeServerResponse
import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.presentation.presenter.ExchangeInitFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ExchangeInitView
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
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
        recyclerViewPokedex.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        presenter.start(compositeDisposable)
        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userIdStr = sharedPreferences?.getString("user-id", "null")
        exchangeInitButton.setOnClickListener {
            if (userIdTextEdit.text!!.isNotBlank() && pokemonId != null) {
                presenter.initExchange(userIdStr!!, pokemonId!!, userIdTextEdit.text.toString(), compositeDisposable)
            } else {
                Toast.makeText(context, "Veillez remplir tous les champs", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun showPokemons(pokemonsList: Pokemons) {
        val adapter = PokemonAdapter(pokemonsList, presenter)
        recyclerViewPokedex.adapter = adapter
    }

    override fun updatePokemonSelected(pokemonId: String) {
        this.pokemonId = pokemonId
        pokemonIdTextView.text = pokemonId
    }

    override fun executeResponse(exchangeServerResponse: ExchangeServerResponse) {
        if (exchangeServerResponse.error.isNullOrBlank()) {
            showExchangeFragment()
        } else {
            Toast.makeText(context, exchangeServerResponse.error, Toast.LENGTH_LONG).show()
        }
    }

    fun showExchangeFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, ExchangeFragment())
            .commit()
    }

    override fun displayLoader() {
        loader.show()
    }

    override fun hideLoader() {
        loader.hide()
    }

    override fun showError(errorMessage: Throwable) {
        Toast.makeText(context, errorMessage.localizedMessage, Toast.LENGTH_LONG).show()
    }

}