package com.example.lpiem.pokecard.presentation.presenter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BasePresenter
import com.example.lpiem.pokecard.data.entity.CancelExchange
import com.example.lpiem.pokecard.data.entity.Exchange
import com.example.lpiem.pokecard.data.entity.SecondResponse
import com.example.lpiem.pokecard.data.entity.UserId
import com.example.lpiem.pokecard.data.network.PokeCardService
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.presentation.ui.adapter.ExchangeRequestsAdapter
import com.example.lpiem.pokecard.presentation.ui.fragments.ExchangeFirstRespondFragment
import com.example.lpiem.pokecard.presentation.ui.fragments.PokeshopBuyFragment
import com.example.lpiem.pokecard.presentation.ui.fragments.PokeshopFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExchangeFragmentPresenter
@Inject constructor(private val service: PokeCardService, private val context: Context) : BasePresenter<ExchangeView>(), ExchangeRequestsAdapter.ClickOnRecycler {


    fun getExchangeRequestsForUser(userId : UserId,compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.getExchangeRequestsForUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it.localizedMessage)
                    },
                    onSuccess = {
                        view.hideLoader()
                        view.showExchangeRequests(it)
                    }
                )
        )
    }

    fun secondrespondtoexchange(secondResponse: SecondResponse, compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            service.secondrespondtoexchange(secondResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        view.hideLoader()
                        view.showError(it.localizedMessage)
                    },
                    onSuccess = {
                        view.hideLoader()
                    }
                )
        )
    }

    override fun showAlertView(
        exchange : Exchange,
        context: Context,
        compositeDisposable: CompositeDisposable
    ) {
            val alertDialogBuilder = AlertDialog.Builder(
                context
            )

            // set title
            alertDialogBuilder.setTitle("Echanger un Pokémon")

            if(exchange.phase == 1) {
                val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
                val userIdStr = sharedPreferences?.getString("user-id", "null")
                val userId = UserId(userIdStr!!)
                if(exchange.userId1 != userId.userId) {
                    view.showExchangeFirstRespondFragment(exchange.id)
                    return
                } else {
                    // set dialog message
                    alertDialogBuilder
                        .setMessage("Vous ne pouvez pas encore accepter l'échange car l'utilisateur 2 n'a pas encore choisi son pokémon, vous pouvez patienter ou annuler l'échange.\nVoulez-vous annuler l'échange ?")
                        .setCancelable(false)
                        .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, id ->
                            val cancelExchange = CancelExchange(userIdStr, exchange.id)
                            service.cancelExchange(cancelExchange)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeBy(
                                    onError = {
                                        view.showError(it.localizedMessage)
                                    },
                                    onSuccess = {
                                        if(it.error == null) {
                                            getExchangeRequestsForUser(userId,compositeDisposable)
                                        } else {
                                            view.showError(it.error)
                                        }
                                    }
                                )


                        })
                        .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, id ->
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel()
                        })
                }

            } else {
                // set dialog message
                alertDialogBuilder
                    .setMessage("Acceptez-vous cet échange ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, id ->

                        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
                        val userIdStr = sharedPreferences?.getString("user-id", "null")
                        val userId = UserId(userIdStr!!)
                        val secondResponse = SecondResponse(exchange.id, userIdStr,"yes")
                        service.secondrespondtoexchange(secondResponse)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeBy(
                                onError = {
                                    view.hideLoader()
                                    view.showError(it.localizedMessage)
                                },
                                onSuccess = {
                                    view.hideLoader()
                                    getExchangeRequestsForUser(userId,compositeDisposable)
                                }
                            )
                    })
                    .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, id ->

                        val sharedPreferences = context!!.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
                        val userIdStr = sharedPreferences?.getString("user-id", "null")
                        val userId = UserId(userIdStr!!)
                        val secondResponse = SecondResponse(exchange.id, userIdStr,"no")
                        service.secondrespondtoexchange(secondResponse)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeBy(
                                onError = {
                                    view.hideLoader()
                                    view.showError(it.localizedMessage)
                                },
                                onSuccess = {
                                    view.hideLoader()
                                    if(it.error != null) {
                                        view.showError(it.error)
                                    } else {
                                        getExchangeRequestsForUser(userId,compositeDisposable)
                                    }
                                }
                            )
                    })
            }



            // create alert dialog
            val alertDialog = alertDialogBuilder.create()

            // show it
            alertDialog.show()
        }


}