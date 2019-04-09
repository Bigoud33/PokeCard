package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Exchange
import com.example.lpiem.pokecard.data.entity.Exchanges
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.exchange_request_item.view.*




class ExchangeRequestsAdapter(val exchangeRequests : Exchanges, val context: Context, private val listener: ClickOnRecycler, val compositeDisposable : CompositeDisposable) : RecyclerView.Adapter<ExchangeRequestsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
                R.layout.exchange_request_item,
                parent,
                false
        )
        return ViewHolder(view, listener, compositeDisposable)
    }

    override fun getItemCount(): Int {
        return exchangeRequests.exchanges.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(exchangeRequests.exchanges[position], position)
    }


    class ViewHolder (view: View, private val listener: ClickOnRecycler, val compositeDisposable : CompositeDisposable) : RecyclerView.ViewHolder(view) {
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
                listener.showAlertView(data, context, compositeDisposable)
            }
        }


    }

    interface ClickOnRecycler {
        fun showAlertView(exchange: Exchange, context: Context, compositeDisposable: CompositeDisposable)
    }
}