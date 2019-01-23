package com.example.lpiem.pokecard.data.network

import com.example.lpiem.pokecard.data.entity.Pokemon
import io.reactivex.Single
import retrofit2.http.GET

interface PokeCardService {

    @GET("api/v2/pokemon/{id}")
    fun getPokemons():
            Single<List<Pokemon>>
}