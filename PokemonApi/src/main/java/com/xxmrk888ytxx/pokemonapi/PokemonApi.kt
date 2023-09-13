package com.xxmrk888ytxx.pokemonapi

import com.xxmrk888ytxx.pokemonapi.models.PokemonListItemRemoteModel

interface PokemonApi {

    suspend fun getPokemonList(offset:Int,limit:Int) : List<PokemonListItemRemoteModel>
}