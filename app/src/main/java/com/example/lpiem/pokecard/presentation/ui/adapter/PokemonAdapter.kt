package com.example.lpiem.pokecard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.entity.Pokemon
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.pokemon_item.view.*
import java.util.ArrayList

class PokemonAdapter(private val pokemons: ArrayList<Pokemon>, private val listener: ClickOnRecycler) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
        val picture: AppCompatImageView = itemView.pokemonPicture
        val name: AppCompatTextView = itemView.pokemonName

        fun bindItems(data: Pokemon, position: Int) {

            name.text = data.name



            itemView.setOnClickListener {
                listener.itemClicked(position, context)
            }
        }
    }

    interface ClickOnRecycler {
        fun itemClicked(position: Int, context: Context)
    }
}