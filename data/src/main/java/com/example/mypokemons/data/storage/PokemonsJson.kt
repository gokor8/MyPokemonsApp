package com.example.mypokemons.data.storage

data class PokemonsJson(
    val count: Int?,
    val data: List<Data>,
    val page: Int?,
    val pageSize: Int?,
    val totalCount: Int?
)