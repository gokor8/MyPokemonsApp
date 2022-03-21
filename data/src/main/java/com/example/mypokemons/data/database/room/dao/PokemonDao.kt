package com.example.mypokemons.data.database.room.dao

import androidx.room.*
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.storage.PokemonInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getAll(): Single<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getById(id: Int): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(pokemons: List<PokemonEntity>)

    @Query("SELECT count(*) FROM pokemons")
    fun getSize(): Single<Int>

    @Query("DELETE FROM pokemons")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(pokemon: PokemonEntity)

    @Update
    fun updateAll(pokemons: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)
}