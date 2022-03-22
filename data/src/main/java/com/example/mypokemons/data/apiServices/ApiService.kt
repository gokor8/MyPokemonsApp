package com.example.mypokemons.data.apiServices

import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/cards/")
    fun getAllCards(): Single<PokemonModel>

    @GET("/v2/cards?")
    fun getFoundCards(@Query("q") name: String): Observable<PokemonModel>

}