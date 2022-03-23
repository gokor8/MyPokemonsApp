package com.example.mypokemons.data.api_services.gson_models

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("data")
    val pokemons: List<PokemonInfo>
)