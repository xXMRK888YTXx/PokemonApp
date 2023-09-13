package com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository

import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.models.PokemonItem

interface PokemonListItemRepository {

    suspend fun getPokemonList(offset:Int,limit:Int) : List<PokemonItem>

}