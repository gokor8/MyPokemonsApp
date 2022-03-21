package com.example.domain.models.model

import com.example.mypokemons.data.storage.Images

data class BasePokemonModel(
    val name: String,
    val image: String
) {

    companion object{
        fun<T> cast(srcModel: List<T>, casting: (T)->BasePokemonModel): List<BasePokemonModel> {
            val castedList = mutableListOf<BasePokemonModel>()
            srcModel.forEach {
                castedList.add(casting(it))
            }

            return castedList
        }
    }
}