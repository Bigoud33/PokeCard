package com.example.lpiem.pokecard.data.network

import com.example.lpiem.pokecard.data.entity.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PokeCardService {

    @GET("pokemons/")
    fun getPokemons():
            Single<Pokemons>

    @GET("user/{userId}/pokemons/")
    fun getPokemonsForUser(@Path("userId") userId: String):
            Single<Pokemons>

    @GET("user/{userId}/")
    fun getProfile(@Path("userId") userId: String):
            Single<User>

    @POST("signup/")
    fun signup(@Body signupUser: SignupUser):
            Single<SignResponse>

    @POST("signin/")
    fun signin(@Body signinUser: SigninUser):
            Single<SignResponse>

    @POST("signintoken/")
    fun signinToken(@Body token: Token):
            Single<SignResponse>

    @POST("signinfacebookgoogle/")
    fun signinFacebookGoogle(@Body signinUser: SigninUser):
            Single<SignResponse>

    @GET("pokemon/{pokemonId}/")
    fun getPokemon(@Path("pokemonId") pokemonId: String):
            Single<PokemonDetails>

    @POST("exchanges/")
    fun getExchangeRequestsForUser(@Body userId: UserId):
            Single<Exchanges>

    @POST("initexchange/")
    fun initExchangeRequest(@Body exchangeRequest: ExchangeRequest):
            Single<ExchangeServerResponse>
    @POST("secondrespondtoexchange/")
    fun secondrespondtoexchange(@Body secondResponse : SecondResponse):
            Single<ExchangeServerResponse>


}