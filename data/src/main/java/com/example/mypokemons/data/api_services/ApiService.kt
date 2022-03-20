package com.example.mypokemons.data.api_services

import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2/cards/")
    fun getAllCards(): Response<PokemonModel>

    @GET("/v2/cards?")
    fun getFoundCards(@Query("q") name: String): Response<PokemonModel>

}