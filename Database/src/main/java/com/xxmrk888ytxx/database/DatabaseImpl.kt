package com.xxmrk888ytxx.database

import android.content.Context
import androidx.room.Room
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSourceImpl

internal class DatabaseImpl(private val context: Context) : Database {

    private val appDatabase by lazy {
        Room.databaseBuilder(context,AppDatabase::class.java,"database.db").build()
    }

    override val pokemonListDataSource: PokemonListDataSource by lazy {
        PokemonListDataSourceImpl(appDatabase.pokemonListItemsDao)
    }
}