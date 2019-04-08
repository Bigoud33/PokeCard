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
import com.example.lpiem.pokecard.data.entity.Exchanges
import kotlinx.android.synthetic.main.exchange_request_item.view.*




class ExchangeRequestsAdapter(val exchangeRequests : Exchanges, val context: Context) : RecyclerView.Adapter<ExchangeRequestsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
                R.layout.exchange_request_item,
                parent,
                false
        )
        view.setOnClickListener {
            showAlertView()
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return exchangeRequests.exchanges.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvUserId1.text = exchangeRequests.exchanges[position].userId1
        holder?.tvPokemonToExchange1.text = exchangeRequests.exchanges[position].pokemonToExchange1.name
       Glide.with(context)
            .load(exchangeRequests.exchanges[position].pokemonToExchange1.sprite)
            .into(holder.ivPokemon1)
        holder?.tvUserId2.text = exchangeRequests.exchanges[position].userId2
        if(exchangeRequests.exchanges[position].phase != 1) {
            holder?.tvPokemonToExchange2.text = exchangeRequests.exchanges[position].pokemonToExchange2.name
            Glide.with(context)
                        .load(exchangeRequests.exchanges[position].pokemonToExchange2.sprite)
                        .into(holder.ivPokemon2)
        } else {
            holder?.tvPokemonToExchange2.text = "En attente"
        }


    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var tvUserId1 = view.userId1
        var tvPokemonToExchange1 = view.pokemonToExchange1
        var tvUserId2 = view.userId2
        var tvPokemonToExchange2 = view.pokemonToExchange2
        var ivPokemon1 = view.pokemonSprite1
        var ivPokemon2 = view.pokemonSprite2


    }

    fun showAlertView() {
        val alertDialogBuilder = AlertDialog.Builder(
            context
        )

        //val itemPosition = ViewHolder.getChildLayoutPosition(view)

        // set title
        alertDialogBuilder.setTitle("Echanger un Pokémon")


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

        // create alert dialog
        val alertDialog = alertDialogBuilder.create()

        // show it
        alertDialog.show()
    }
}