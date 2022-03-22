package com.example.mypokemons.data.storage

data class PokemonInfo(
    val attacks: List<Attack>,
    val hp: String,
    val images: Images,
    val name: String,
    val rarity: String,
    val subtypes: List<String>,
    val supertype: String,
    val types: List<String>,
)