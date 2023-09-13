package com.xxmrk888ytxx.database.dataSources

import com.xxmrk888ytxx.database.models.PokemonDetailsLocalModel

interface PokemonDetailsDataSource {

    suspend fun getPokemonDetails(pokemonId:Int) : PokemonDetailsLocalModel?

    suspend fun insert(pokemonDetailsLocalModel: PokemonDetailsLocalModel)
}