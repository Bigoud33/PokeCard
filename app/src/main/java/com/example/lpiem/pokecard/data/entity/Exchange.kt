package com.example.lpiem.pokecard.data.entity

data class Exchange(val id: String, val userId1: String, val pokemonToExchange1: Pokemon, val pokemonToExchange2: Pokemon, val userId2: String, val phase : Int)