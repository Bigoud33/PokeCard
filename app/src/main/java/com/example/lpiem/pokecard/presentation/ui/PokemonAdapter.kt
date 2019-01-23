package com.example.lpiem.pokecard.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Pokemon
import java.util.ArrayList

class PokemonAdapter(private val pokemons: ArrayList<Pokemon>, private val listener: ClickOnRecycler) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(pokemons[position], position)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class ViewHolder(view: View, private val listener: ClickOnRecycler) : RecyclerView.ViewHolder(view) {
        val context: Context = itemView.context

        fun bindItems(data: Pokemon, position: Int) {





            itemView.setOnClickListener {
                listener.itemClicked(position, context)
            }
        }
    }

    interface ClickOnRecycler {
        fun itemClicked(position: Int, context: Context)
    }
}