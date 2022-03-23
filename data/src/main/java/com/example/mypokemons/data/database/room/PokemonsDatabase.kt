package com.example.mypokemons.data.database.room

import androidx.room.*
import androidx.room.RoomDatabase
import com.example.mypokemons.data.database.room.dao.PokemonDao

@Database(entities = [(PokemonEntity::class)], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}