package com.example.mypokemons.data.storage

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("data")
    val pokemons: List<PokemonInfo>
)