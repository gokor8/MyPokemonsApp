package com.example.mypokemons.data.models

import com.example.mypokemons.data.ApiService.PokemonApiService

class PokemonsModel {

    private val retrofitService = PokemonApiService.getInstance()

    fun getAllCards() = retrofitService.getAllCards()
}