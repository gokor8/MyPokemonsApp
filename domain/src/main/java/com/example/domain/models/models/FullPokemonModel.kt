package com.example.domain.models.models

class FullPokemonModel(
    id: String,
    name: String,
    image: String,
    isFavorite: Boolean,
    val rare: String,
    val type: String,
    val subtype: String,
    val health: String,
    val typeAttack: String,
) : BasePokemonModel(
    id, name, image, isFavorite
) {}