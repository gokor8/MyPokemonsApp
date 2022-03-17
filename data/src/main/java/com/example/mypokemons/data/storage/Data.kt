package com.example.mypokemons.data.storage

data class Data(
    val id: String,
    val images: Images,
    val legalities: Legalities,
    val name: String,
    val printedTotal: Int,
    val ptcgoCode: String,
    val releaseDate: String,
    val series: String,
    val total: Int,
    val updatedAt: String
)