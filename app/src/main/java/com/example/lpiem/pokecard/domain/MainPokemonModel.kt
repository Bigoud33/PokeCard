package com.example.lpiem.pokecard.domain

import com.example.lpiem.pokecard.data.entity.Pokemon
import com.example.lpiem.pokecard.data.network.PokeCardService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPokemonModel
@Inject constructor(private val service: PokeCardService) {
    fun getPokemons(): Single<List<Pokemon>> =
        service
            .getPokemons()
            .subscribeOn(Schedulers.io())
}