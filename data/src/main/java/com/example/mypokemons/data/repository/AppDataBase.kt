package com.example.mypokemons.data.repository

import android.app.Application
import androidx.room.Room

object AppDataBase{

    val dbInstance by lazy {
        Room.databaseBuilder(
            this,
            PokemonsDatabase::class.java, "book_database"
        ).build()
    }
}