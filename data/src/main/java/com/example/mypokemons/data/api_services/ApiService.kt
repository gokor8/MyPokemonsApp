package com.example.mypokemons.data.api_services

import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v2/cards/")
    fun getAllCards(): Single<PokemonModel>

    @GET("/v2/cards?q=set.name:{name}")
    fun getFoundCards(@Path("name") name: String): Single<PokemonModel>

    companion object {
        val apiService by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}