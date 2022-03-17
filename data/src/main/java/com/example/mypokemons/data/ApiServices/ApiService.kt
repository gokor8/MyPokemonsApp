package com.example.mypokemons.data.ApiServices

import com.example.mypokemons.data.storage.PokemonsJson
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    //https://api.pokemontcg.io/
    @GET("v2/sets")
    suspend fun getAllCards(): Response<PokemonsJson>

    companion object {
        val retrofitService by lazy {
                Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: ApiService by lazy {
            retrofitService.create(ApiService::class.java)
        }
    }
}