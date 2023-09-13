package com.xxmrk888ytxx.database.dataSources

import com.xxmrk888ytxx.database.models.PokemonListItemLocalModel

interface PokemonListDataSource {

    suspend fun getItems(offset:Int = 0,limit:Int = 20) : List<PokemonListItemLocalModel>

    suspend fun addItem(pokemonListItemLocalModel: PokemonListItemLocalModel)
}