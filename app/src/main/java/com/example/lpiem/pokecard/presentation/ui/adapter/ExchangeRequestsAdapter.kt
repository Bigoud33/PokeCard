package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Exchanges
import com.example.lpiem.pokecard.presentation.ui.picasso.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.exchange_request_item.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

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
        Picasso.get()
            .load(exchangeRequests.results[position].pokemonSprite1)
            .transform(CircleTransform())
            .into(holder.ivPokemon1)
        holder?.tvUserId2.text = exchangeRequests.results[position].userId2.capitalize()
        holder?.tvPokemonToExchange2.text = exchangeRequests.results[position].pokemonToExchange2.name
        Picasso.get()
            .load(exchangeRequests.results[position].pokemonSprite2)
            .transform(CircleTransform())
            .into(holder.ivPokemon2)
    }


    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var tvUserId1 = view.userId1
        var tvPokemonToExchange1 = view.pokemonToExchange1
        var tvUserId2 = view.pokemonToExchange2
        var tvPokemonToExchange2 = view.pokemonToExchange2
        var ivPokemon1 = view.pokemonSprite1
        var ivPokemon2 = view.pokemonSprite2
    }
}