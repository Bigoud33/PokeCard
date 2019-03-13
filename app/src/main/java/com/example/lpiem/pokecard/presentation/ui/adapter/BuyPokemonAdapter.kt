package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.item_recycler_view_shop_buy.view.*
import java.util.ArrayList

class BuyPokemonAdapter(val data : /*Temp*/ ArrayList<String>/*ArrayList<Pokemon>*/, val context: Context?) : RecyclerView.Adapter<BuyPokemonAdapter.ViewHolder>()  {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder?.tvNamePokemon.text =  data.name
        //holder?.tvPokemonPrice.text = data.price
        //holder?.ivPokemonSprite.
        holder?.tvNamePokemon.text = data[position]

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_shop_buy, parent, false))
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view_shop_buy,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        var tvNamePokemon = view.pokemonName
        //var tvPokemonPrice = view.pokemonPrice
        //var ivPokemonSprite = view.pokemonSprite
    }

}



