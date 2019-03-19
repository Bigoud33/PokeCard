package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Pokemons
import kotlinx.android.synthetic.main.item_recycler_view_shop_buy.view.*
import java.net.URL

class BuyPokemonAdapter(val pokemons : Pokemons/*Temp ArrayList<String> */, val context: Context?) : RecyclerView.Adapter<BuyPokemonAdapter.ViewHolder>()  {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvNamePokemon.text = pokemons.pokemons[position].name
        holder?.tvPokemonPrice.text = pokemons.pokemons[position].price.toString()
        val spriteURL = URL(pokemons.pokemons[position].sprite)
        Glide.with(context)
            .load(spriteURL)
            .into(holder?.ivPokemonSprite)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_shop_buy, parent, false))
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_shop_buy,parent,false))
    }

    override fun getItemCount(): Int {
        return pokemons.pokemons.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var tvNamePokemon = view.pokemonName
        var tvPokemonPrice = view.pokemonPrice
        var ivPokemonSprite = view.pokemonSprite
    }

}



