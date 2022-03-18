package com.example.domain.models

import com.example.mypokemons.data.ApiServices.ApiService

class PokemonsModel {

    private val retrofitService = ApiService.api

    fun getAllCards() = retrofitService.getAllCards()
}