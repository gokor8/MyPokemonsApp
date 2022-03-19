package com.example.mypokemons.data.database.room.dao

import androidx.room.*
import com.example.mypokemons.data.database.room.PokemonEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getById(id: Int): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(pokemons: List<PokemonEntity>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemon: PokemonEntity)

    @Update
    fun updateAll(pokemons: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)
}