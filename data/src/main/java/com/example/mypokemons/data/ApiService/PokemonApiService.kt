package com.example.mypokemons.data.ApiService

import com.example.mypokemons.data.PokemonBaseInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PokemonApiService {
    @GET("https://api.pokemontcg.io/v2/sets")
    fun getAllCards(): Call<List<PokemonBaseInfo>>

    companion object {
        var retrofitService: PokemonApiService? = null

        fun getInstance() : PokemonApiService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(PokemonApiService::class.java)
            }
            return retrofitService!!
        }
    }
}