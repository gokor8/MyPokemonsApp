package com.example.domain.models

import com.example.mypokemons.data.ApiServices.ApiService

class AllPokemonsCardsModel {

    private val retrofitService = ApiService.api

    fun getAllCards() = retrofitService.getAllCards()
}