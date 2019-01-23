package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.Pokemon
import com.example.lpiem.pokecard.presentation.presenter.PokedexFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokedexView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokedex.*
import java.util.ArrayList
import javax.inject.Inject

class PokedexFragment : BaseFragment<PokedexFragmentPresenter>(), PokedexView {

    override val layoutId: Int = R.layout.fragment_pokedex

    @Inject
    override lateinit var presenter: PokedexFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        recyclerViewPokedex.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val pokemons = ArrayList<Pokemon>()
        val adapter =
    }

    override fun displayLoader() {
        progress_bar.show()
    }

    override fun hideLoader() {
        progress_bar.hide()
    }

    override fun showPokemons(pokemons: List<Pokemon>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}