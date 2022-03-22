package com.example.mypokemons.data.database.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.storage.PokemonInfo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun getAll(): Single<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE name = '%' || :name  || '%'")
    fun getByName(name: String): Observable<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE name LIKE '%' || :name  || '%'")
    fun getEqualsByName(name: String): Observable<List<PokemonEntity>>

    @Insert(onConflict = REPLACE)
    fun insertList(pokemons: List<PokemonEntity>)

    @Query("SELECT count(*) FROM pokemons")
    fun getSize(): Single<Int>

    @Query("DELETE FROM pokemons")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun update(pokemon: PokemonEntity)

    @Query("UPDATE pokemons SET is_favorite = :newFavoriteStatus WHERE name = :name")
    fun updateFavoriteByName(name: String, newFavoriteStatus: Int): Single<Unit>

    @Query("select * FROM pokemons WHERE name = :name")
    fun getFavoriteByName(name: String) : Observable<PokemonEntity>

    @Query("select * FROM pokemons WHERE name = :name")
    fun getCardByName(name: String) : Observable<PokemonEntity>

    @Update
    fun updateAll(pokemons: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)
}