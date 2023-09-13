package com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource

import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.models.PagingLoadingResult
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.models.PokemonItem
import kotlinx.coroutines.flow.Flow

interface PagingPokemonsDataSource {

    val pokemons: Flow<List<PokemonItem>>

    val isDataLoading:Flow<Boolean>

    suspend fun loadNextPage() : Result<PagingLoadingResult>

    suspend fun reset()
}