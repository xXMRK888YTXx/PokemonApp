package com.xxmrk888ytxx.database

import android.content.Context
import com.xxmrk888ytxx.database.dataSources.PokemonDetailsDataSource
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource

interface Database {

    val pokemonListDataSource:PokemonListDataSource

    val pokemonDetailsDataSource:PokemonDetailsDataSource

    companion object {

        fun create(context: Context) : Database = DatabaseImpl(context)
    }
}