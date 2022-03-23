package com.example.lib.di

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