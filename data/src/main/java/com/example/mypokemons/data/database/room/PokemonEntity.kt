package com.example.mypokemons.data.database.room

import androidx.room.*
import com.example.mypokemons.data.database.room.converters.ImagesConverter
import com.example.mypokemons.data.database.room.models.Images
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "pokemons")
@TypeConverters(ImagesConverter::class)
data class PokemonEntity(
    @ColumnInfo(name = "name")
    val name: String,
    val rare: String,
    val type: String,
    val subtype: String,
    val health: String,
    val images: Images,
    @NotNull
    @ColumnInfo(name = "type_attack")
    val typeAttack: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
