package com.example.lpiem.pokecard.presentation.presenter

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.CancelExchange
import com.example.lpiem.pokecard.data.entity.FirstResponse
import com.example.lpiem.pokecard.data.network.PokeCardService
import com.example.lpiem.pokecard.presentation.ui.adapter.ExchangeRequestsAdapter
import com.example.lpiem.pokecard.presentation.ui.adapter.PokemonAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeFirstRespondFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<ExchangeFirstRespondView>(), PokemonAdapter.ClickOnRecycler{

    override fun itemClicked(pokemonId: String, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(
            context
        )

        // set title
        alertDialogBuilder.setTitle("Echanger un Pokémon")

        alertDialogBuilder
            .setMessage("Voulez-vous vraiment échanger ce pokémon ?")
            .setCancelable(false)
            .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, id ->
                view.goNext(pokemonId)


            })
            .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, id ->
                // if this button is clicked, just close
                // the dialog box and do nothing
                dialog.cancel()
            })
        // create alert dialog
        val alertDialog = alertDialogBuilder.create()

        // show it
        alertDialog.show()
    }

    fun getPokemonsForUser(userId: String, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getPokemonsForUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.showPokemons(it)
                    }
                )
        )
    }

    fun respondToExchange(firstResponse: FirstResponse, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.respondToExchange(firstResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it)
                    },
                    onSuccess = {
                        if(it.error == null) {

                        } else {
                            view.showError2(it.error)
                        }
                    }
                )
        )

    }


}