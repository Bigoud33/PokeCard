package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Exchanges
import kotlinx.android.synthetic.main.exchange_request_item.view.*

class ExchangeRequestsAdapter(val exchangeRequests : Exchanges, val context: Context?) : RecyclerView.Adapter<ExchangeRequestsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ExchangeRequestsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.exchange_request_item, parent, false))
    }

    override fun getItemCount(): Int {
        return exchangeRequests.results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvUserId1.text = exchangeRequests.results[position].userId1.capitalize()
        holder?.tvPokemonToExchange1.text = exchangeRequests.results[position].pokemonToExchange1.name
        holder?.tvUserId2.text = exchangeRequests.results[position].userId2.capitalize()
        holder?.tvPokemonToExchange2.text = exchangeRequests.results[position].pokemonToExchange2.name
    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var tvUserId1 = view.userId1
        var tvPokemonToExchange1 = view.pokemonToExchange1
        var tvUserId2 = view.userId2
        var tvPokemonToExchange2 = view.pokemonToExchange2

    }
}