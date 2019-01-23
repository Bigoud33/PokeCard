package com.example.lpiem.pokecard.data.network

import com.example.lpiem.pokecard.data.entity.Pokemons
import io.reactivex.Single
import retrofit2.http.GET

interface PokeCardService {

    @GET("pokemon/")
    fun getPokemons():
            Single<Pokemons>
}