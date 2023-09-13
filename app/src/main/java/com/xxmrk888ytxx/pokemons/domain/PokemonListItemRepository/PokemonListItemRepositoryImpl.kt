package com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository

import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource
import com.xxmrk888ytxx.database.models.PokemonListItemLocalModel
import com.xxmrk888ytxx.pokemonapi.PokemonApi
import com.xxmrk888ytxx.pokemonapi.models.PokemonListItemRemoteModel
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.models.PokemonItem
import javax.inject.Inject

class PokemonListItemRepositoryImpl @Inject constructor(
    private val pokemonListDataSource: PokemonListDataSource,
    private val pokemonApi: PokemonApi
) : PokemonListItemRepository {

    override suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonItem> {
        val pokemonsFromLocalDataSource = pokemonListDataSource.getItems(offset, limit)

        return if(pokemonsFromLocalDataSource.size == limit) {
            pokemonsFromLocalDataSource.map { it.toPokemonItem() }
        } else {
            val pokemonsFromRemoteDataSource = pokemonApi.getPokemonList(offset, limit)

            pokemonsFromRemoteDataSource.forEach {
                pokemonListDataSource.addItem(it.toLocalSourceModel())
            }

            pokemonListDataSource.getItems(offset, limit).map { it.toPokemonItem() }
        }
    }

    private fun PokemonListItemLocalModel.toPokemonItem() : PokemonItem {
        return PokemonItem(this.remoteId,this.name)
    }

    private fun PokemonListItemRemoteModel.toLocalSourceModel() : PokemonListItemLocalModel {
        return PokemonListItemLocalModel(remoteId, name)
    }
}