package com.example.mypokemons.data.database.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mypokemons.data.database.room.PokemonEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface PokemonDao {

    companion object{
        const val FAVORITE = 1
        const val UNFAVORITE = 0
    }

    @Query("SELECT * FROM pokemons")
    fun getAll(): Single<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE name = '%' || :name  || '%'")
    fun getByName(name: String): Observable<List<PokemonEntity>>

    @Query("SELECT * FROM pokemons WHERE name LIKE '%' || :name  || '%'")
    fun getEqualsByName(name: String): Observable<List<PokemonEntity>>

    @Query("SELECT count(*) FROM pokemons")
    fun getSize(): Single<Int>

    @Query("SELECT * FROM pokemons WHERE name = :name")
    fun getFavoriteByName(name: String) : Observable<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE name = :name")
    fun getCardByName(name: String) : Observable<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE is_favorite = :favorite")
    fun getCardsByFavorite(favorite: Int) : Observable<List<PokemonEntity>>

    @Query("UPDATE pokemons SET is_favorite = :newFavoriteStatus WHERE name = :name")
    fun updateFavoriteByName(name: String, newFavoriteStatus: Int): Single<Unit>

    @Query("DELETE FROM pokemons")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun insertList(pokemons: List<PokemonEntity>)

    @Insert(onConflict = REPLACE)
    fun update(pokemon: PokemonEntity)

    @Update
    fun updateAll(pokemons: List<PokemonEntity>)

    @Delete
    fun delete(pokemon: PokemonEntity)
}