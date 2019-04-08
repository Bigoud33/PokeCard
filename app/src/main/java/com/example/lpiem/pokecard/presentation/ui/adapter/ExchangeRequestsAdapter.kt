package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Exchange
import com.example.lpiem.pokecard.data.entity.Exchanges
import kotlinx.android.synthetic.main.exchange_request_item.view.*




class ExchangeRequestsAdapter(val exchangeRequests : Exchanges, val context: Context, private val listener: ClickOnRecycler) : RecyclerView.Adapter<ExchangeRequestsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
                R.layout.exchange_request_item,
                parent,
                false
        )
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return exchangeRequests.exchanges.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(exchangeRequests.exchanges[position], position)
    }


    class ViewHolder (view: View, private val listener: ClickOnRecycler) : RecyclerView.ViewHolder(view) {
        val context: Context = itemView.context

        var tvUserId1 = view.userId1
        var tvPokemonToExchange1 = view.pokemonToExchange1
        var tvUserId2 = view.userId2
        var tvPokemonToExchange2 = view.pokemonToExchange2
        var ivPokemon1 = view.pokemonSprite1
        var ivPokemon2 = view.pokemonSprite2

        fun bindItems(data : Exchange, position : Int) {
            tvUserId1.text = data.userId1
            tvPokemonToExchange1.text = data.pokemonToExchange1.name
            Glide.with(context)
                .load(data.pokemonToExchange1.sprite)
                .into(ivPokemon1)
            tvUserId2.text = data.userId2
            if(data.phase != 1) {
                tvPokemonToExchange2.text = data.pokemonToExchange2.name
                Glide.with(context)
                    .load(data.pokemonToExchange2.sprite)
                    .into(ivPokemon2)
            } else {
                tvPokemonToExchange2.text = "En attente"
            }

            itemView.setOnClickListener {
                listener.showAlertView(data.phase, context)
            }
        }


    }

    interface ClickOnRecycler {
        fun showAlertView(phase : Int, context: Context) {
            val alertDialogBuilder = AlertDialog.Builder(
                context
            )

            // set title
            alertDialogBuilder.setTitle("Echanger un Pokémon")

            if(phase == 1) {
                // set dialog message
                alertDialogBuilder
                    .setMessage("Vous ne pouvez pas encore accepter l'échange car l'utilisateur 2 n'a pas encore choisi son pokémon, vous pouvez patienter ou annuler l'échange.\nVoulez-vous annuler l'échange ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, id ->
                        // if this button is clicked, close
                        // current activity


                    })
                    .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, id ->
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel()
                    })
            } else {
                // set dialog message
                alertDialogBuilder
                    .setMessage("Acceptez-vous cet échange ?")
                    .setCancelable(false)
                    .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, id ->
                        // if this button is clicked, close
                        // current activity

                    })
                    .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, id ->
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel()
                    })
            }



            // create alert dialog
            val alertDialog = alertDialogBuilder.create()

            // show it
            alertDialog.show()
        }
    }
}