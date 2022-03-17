package com.example.mypokemons.data.repository

import androidx.room.*

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getById(id: Int): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(pokemons: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemon: PokemonEntity)

    @Update
    fun updateAll(pokemons: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)
}