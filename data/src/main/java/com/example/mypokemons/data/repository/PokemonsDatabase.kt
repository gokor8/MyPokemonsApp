package com.example.mypokemons.data.repository

import androidx.room.*
import androidx.room.RoomDatabase
import com.example.mypokemons.data.repository.PokemonDao
import com.example.mypokemons.data.repository.PokemonEntity

@Database(entities = [(PokemonEntity::class)], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}