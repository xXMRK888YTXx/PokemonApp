package com.xxmrk888ytxx.database.dataSources

import com.xxmrk888ytxx.database.dao.PokemonListItemsDao
import com.xxmrk888ytxx.database.entityes.PokemonListItemEntity
import com.xxmrk888ytxx.database.models.PokemonListItemLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PokemonListDataSourceImpl(
    private val pokemonListItemsDao: PokemonListItemsDao
) : PokemonListDataSource {
    override suspend fun getItems(offset: Int, limit: Int): List<PokemonListItemLocalModel> = withContext(Dispatchers.IO) {
        return@withContext pokemonListItemsDao.getPokemonListItems(offset,limit).map {
            it.toModel()
        }
    }

    override suspend fun addItem(pokemonListItemLocalModel: PokemonListItemLocalModel): Unit = withContext(Dispatchers.IO) {
        pokemonListItemsDao.insert(pokemonListItemLocalModel.toEntity())
    }

    private fun PokemonListItemLocalModel.toEntity() : PokemonListItemEntity {
        return PokemonListItemEntity(remoteId,name,detailsUrl)
    }

    private fun PokemonListItemEntity.toModel() : PokemonListItemLocalModel {
        return PokemonListItemLocalModel(id,name,urlFromDetail)
    }
}