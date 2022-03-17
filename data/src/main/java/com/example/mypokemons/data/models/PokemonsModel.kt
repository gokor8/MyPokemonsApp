package com.example.mypokemons.data.models

import com.example.mypokemons.data.ApiServices.ApiService
import com.example.mypokemons.data.storage.PokemonsJson
import retrofit2.Call
import retrofit2.Response

class PokemonsModel {

    private val retrofitService = ApiService.api

    suspend fun getAllCards(): Response<PokemonsJson> = retrofitService.getAllCards()
}