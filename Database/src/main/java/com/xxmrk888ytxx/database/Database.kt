package com.xxmrk888ytxx.database

import android.content.Context
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource

interface Database {

    val pokemonListDataSource:PokemonListDataSource

    companion object {

        fun create(context: Context) : Database = DatabaseImpl(context)
    }
}