package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.PokemonDetails
import com.example.lpiem.pokecard.presentation.presenter.PokemonDetailsFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.PokemonDetailsView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.pokemon_details_fragment.*
import javax.inject.Inject

class PokemonDetailsFragment : BaseFragment<PokemonDetailsFragmentPresenter>(), PokemonDetailsView {

    override val layoutId: Int = R.layout.pokemon_details_fragment
    private val compositeDisposable = CompositeDisposable()

    @Inject
    override lateinit var presenter: PokemonDetailsFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        arguments?.let { arg ->
            presenter.start(arg.getString("POKEMONID")!!, compositeDisposable)
        }

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun displayLoader() {
        progressBar.show()
    }

    override fun hideLoader() {
        progressBar.hide()
    }

    override fun showPokemon(pokemon: PokemonDetails) {
        Glide.with(context)
            .load(pokemon.sprite)
            .into(pokemonImageView)
        pokemonName.text = pokemon.name.capitalize()
        hpLabelValue.text = pokemon.hp.toString()
        speedLabelValue.text = pokemon.speed.toString()
        attackLabelValue.text = pokemon.attack.toString()
        defenseLabelValue.text = pokemon.defense.toString()
        specialAttackLabelValue.text = pokemon.specialAttack.toString()
        specialDefenseLabelValue.text = pokemon.specialDefense.toString()
        price.text = pokemon.price.toString()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(pokemonId: String): PokemonDetailsFragment {
            val pokemonDetailsFragment = PokemonDetailsFragment()
            val bundle = Bundle()
            bundle.putString("POKEMONID", pokemonId)
            pokemonDetailsFragment.arguments = bundle
            return pokemonDetailsFragment
        }
    }

}