package com.example.domain.models.models

open class BasePokemonModel(
    val name: String,
    val image: String,
    val isFavorite: Boolean
) {
    companion object{
        fun<T,R> cast(srcModel: List<T>, casting: (T)->R): List<R> {
            val castedList = mutableListOf<R>()
            srcModel.forEach {
                castedList.add(casting(it))
            }

            return castedList
        }
    }
}