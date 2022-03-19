package com.example.mypokemons.data.database.room

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    //@SerializedName("id")
    val id: Int,
    //@SerializedName("name")
    val name: String,
   // @SerializedName("rare")
    val rare: String,
    //@SerializedName("type")
    val type: String,
    //@SerializedName("subtype")
    val subtype: String,
    //@SerializedName("health")
    val health: String,
   // @SerializedName("type_attack")
    @ColumnInfo(name = "type_attack")
    val typeAttack: String,
)
