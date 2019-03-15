package com.example.lpiem.pokecard.data.network

import com.example.lpiem.pokecard.data.entity.Pokemons
import com.example.lpiem.pokecard.data.entity.SignResponse
import com.example.lpiem.pokecard.data.entity.SigninUser
import com.example.lpiem.pokecard.data.entity.SignupUser
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PokeCardService {

    @GET("pokemon/")
    fun getPokemons():
            Single<Pokemons>

    @POST("signup/")
    fun signup(@Body signupUser: SignupUser):
            Single<SignResponse>

    @POST("signin/")
    fun signin(@Body signinUser: SigninUser):
            Single<SignResponse>


}