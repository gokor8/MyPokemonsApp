package com.example.mypokemons.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.SearchPokemonsModel
import com.example.mypokemons.data.storage.PokemonModel
import io.reactivex.schedulers.Schedulers

class SearchPokemonCardsViewModel : ViewModel() {
    val foundPokemonsLiveData = MutableLiveData<PokemonModel>()

    private val model = SearchPokemonsModel()

    fun search(name: String){
        model.getFoundCards(name)
            .subscribeOn(Schedulers.io())
            .subscribe ({
                foundPokemonsLiveData.postValue(it)
            },{

            })
    }
}