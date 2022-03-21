package com.example.mypokemons.data.di

import com.example.mypokemons.data.apiServices.ApiService
import com.example.mypokemons.data.database.room.dao.PokemonDao
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, ApiModule::class])
interface AppComponent {

    fun getDao(): PokemonDao
    fun getApiService(): ApiService
}