package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Pokemon
import com.example.lpiem.pokecard.data.entity.Pokemons
import kotlinx.android.synthetic.main.pokemon_item.view.*
import java.net.URL

class PokemonAdapter(private val pokemons: Pokemons, private val listener: ClickOnRecycler) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(pokemons.pokemons[position], position)
    }

    override fun getItemCount(): Int {
        return pokemons.pokemons.size
    }

    class ViewHolder(view: View, private val listener: ClickOnRecycler) : RecyclerView.ViewHolder(view) {
        val context: Context = itemView.context
        val picture: AppCompatImageView = itemView.pokemonImageView
        val name: AppCompatTextView = itemView.pokemonName

        fun bindItems(data: Pokemon, position: Int) {

            name.text = data.name.capitalize()
            Glide.with(context)
                .load(data.sprite)
                .into(picture)



            itemView.setOnClickListener {
                listener.itemClicked(data.id, context)
            }
        }
    }

    interface ClickOnRecycler {
        fun itemClicked(pokemonId: String, context: Context)
    }
}