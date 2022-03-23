package com.example.mypokemons.data.api_services

import com.example.mypokemons.data.api_services.gson_models.PokemonModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/cards/")
    fun getAllCards(): Single<PokemonModel>

    @GET("/v2/cards?")
    fun getByNameCards(@Query("q") name: String): Observable<PokemonModel>

}