package com.example.mypokemons.data.ApiService

class MainRepository (private val retrofitService: PokemonApiService) {
    fun getAllCards() = retrofitService.getAllCards()
}
