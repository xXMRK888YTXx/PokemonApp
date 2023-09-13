package com.xxmrk888ytxx.database.dataSources

import com.xxmrk888ytxx.database.dao.PokemonDetailsDao
import com.xxmrk888ytxx.database.dao.PokemonTypesDao
import com.xxmrk888ytxx.database.entityes.PokemonDetailsEntity
import com.xxmrk888ytxx.database.entityes.PokemonTypesEntity
import com.xxmrk888ytxx.database.models.PokemonDetailsLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PokemonDetailsDataSourceImpl(
    private val pokemonDetailsDao: PokemonDetailsDao,
    private val pokemonTypesDao: PokemonTypesDao
) : PokemonDetailsDataSource {
    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetailsLocalModel? = withContext(Dispatchers.IO) {
        val entity = pokemonDetailsDao.getPokemonDetailsById(pokemonId) ?: return@withContext null
        val types = pokemonTypesDao.getPokemonTypes(pokemonId)

        return@withContext PokemonDetailsLocalModel(
            id = entity.id,
            name = entity.name,
            pokemonImageUrl = entity.pokemonImageUrl,
            weight = entity.weight,
            height = entity.height,
            typesName = types.map { it.typeName }
        )
    }

    override suspend fun insert(pokemonDetailsLocalModel: PokemonDetailsLocalModel) = withContext(Dispatchers.IO) {
        pokemonDetailsDao.insert(
            PokemonDetailsEntity(
                id = pokemonDetailsLocalModel.id,
                name = pokemonDetailsLocalModel.name,
                pokemonImageUrl = pokemonDetailsLocalModel.pokemonImageUrl,
                weight = pokemonDetailsLocalModel.weight,
                height = pokemonDetailsLocalModel.height
            )
        )

        pokemonDetailsLocalModel.typesName.forEach {
            pokemonTypesDao.insert(
                PokemonTypesEntity(
                    pokemonId = pokemonDetailsLocalModel.id,
                    typeName = it
                )
            )
        }
    }
}