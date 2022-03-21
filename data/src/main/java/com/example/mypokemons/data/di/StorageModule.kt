package com.example.mypokemons.data.di

import android.content.Context
import androidx.room.Room
import com.example.mypokemons.data.database.room.PokemonsDatabase
import com.example.mypokemons.data.database.room.dao.PokemonDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule(val context: Context) {

    @Provides
    fun provideContext(): Context =
        context

    @Provides
    @Singleton
    fun provideDatabase(
        context: Context
    ): PokemonsDatabase =
        Room.databaseBuilder(
            context,
            PokemonsDatabase::class.java,
            "pokemon_database"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideYourDao(db: PokemonsDatabase): PokemonDao = db.getPokemonDao()

}