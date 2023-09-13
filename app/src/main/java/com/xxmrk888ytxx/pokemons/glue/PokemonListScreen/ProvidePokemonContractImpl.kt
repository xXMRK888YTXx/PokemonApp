package com.xxmrk888ytxx.pokemons.glue.PokemonListScreen

import com.xxmrk888ytxx.pokemonlistscreen.contracts.ProvidePokemonContract
import com.xxmrk888ytxx.pokemonlistscreen.exceptions.NoInternetConnection
import com.xxmrk888ytxx.pokemonlistscreen.exceptions.UnknownException
import com.xxmrk888ytxx.pokemonlistscreen.models.PokemonModel
import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.PagingPokemonsDataSource
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.models.PokemonItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException
import javax.inject.Inject

class ProvidePokemonContractImpl @Inject constructor(
    private val pagingPokemonsDataSource: PagingPokemonsDataSource
) : ProvidePokemonContract {

    override val pokemons: Flow<List<PokemonModel>> = pagingPokemonsDataSource.pokemons.map { list ->
        list.map { it.toScreenModel() }
    }

    override val isLoading: Flow<Boolean> = pagingPokemonsDataSource.isDataLoading

    override suspend fun loadNext(): Result<Boolean> {
        val result = pagingPokemonsDataSource.loadNextPage()

        return if(result.isSuccess) {
            result.map { it.isAllDataLoaded }
        } else {

            when(result.exceptionOrNull()) {
                is UnknownHostException -> Result.failure(NoInternetConnection())

                else -> Result.failure(UnknownException())
            }
        }

    }

    private fun PokemonItem.toScreenModel() : PokemonModel {
        return PokemonModel(remoteId,name)
    }
}