package com.example.mypokemons.view_models

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.AllPokemonsCardsModel
import com.example.mypokemons.data.database.room.PokemonEntity
import com.example.mypokemons.data.database.room.PokemonsDatabase
import com.example.mypokemons.data.database.room.dao.PokemonDao
import com.example.mypokemons.data.storage.PokemonInfo
import com.example.mypokemons.data.storage.PokemonModel
import com.example.mypokemons.ui.BaseApplication
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.notify
import javax.inject.Inject

class PokemonCardsViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonsLiveData = MutableLiveData<List<PokemonInfo>>()

    val appComponent = (application as BaseApplication).getAppComponent()

    val dbDao: PokemonDao = appComponent.getDao()

    private var model = AllPokemonsCardsModel(appComponent)
    private var dataBaseInstance: PokemonsDatabase? = null

    @SuppressLint("CheckResult")
    fun getAllPokemons() {
        model.getAllCards()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { onNext: List<PokemonInfo> ->
                    if (onNext.size != pokemonsLiveData.value?.size)
                        pokemonsLiveData.postValue(onNext)
                },
                { onError ->
                    Log.d("OnErrorRx", "Error")
                    pokemonsLiveData.notify()
                }
            )
    }

    fun getInsert() {
        /*dbDao.insertList(
            listOf(
                PokemonEntity(
                    3, "AA2", "", "", "", "", ""
                ),
                PokemonEntity(
                    2, "AA1", "", "", "", "", ""
                )
            )
        ).subscribeOn(Schedulers.io())

        dbDao.getAll()
            .subscribe {
                Log.d("DbData", it.joinToString(separator = "\r\n"))
            }*/
    }
}
