package com.example.mypokemons.data.ApiServices

import com.example.mypokemons.data.storage.PokemonsDataModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("v2/sets")
    fun getAllCards(): Observable<PokemonsDataModel>

    companion object {
        val retrofitService by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val api: ApiService by lazy {
            retrofitService.create(ApiService::class.java)
        }
    }
}