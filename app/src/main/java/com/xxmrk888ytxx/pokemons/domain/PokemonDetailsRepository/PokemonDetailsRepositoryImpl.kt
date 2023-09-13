package com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Logger
import com.xxmrk888ytxx.database.dataSources.PokemonDetailsDataSource
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource
import com.xxmrk888ytxx.database.models.PokemonDetailsLocalModel
import com.xxmrk888ytxx.pokemonapi.PokemonApi
import com.xxmrk888ytxx.pokemonapi.models.PokemonDetailsRemoteModel
import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.models.PokemonDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailsRepositoryImpl @Inject constructor(
    private val pokemonDetailsDataSource: PokemonDetailsDataSource,
    private val pokemonApi: PokemonApi,
    private val logger: Logger
) : PokemonDetailsRepository {

    override suspend fun getDetails(id: Int): Result<PokemonDetails> = withContext(Dispatchers.IO)  {
        return@withContext try {
            val dataFromLocalDataSource = pokemonDetailsDataSource.getPokemonDetails(id)

            if(dataFromLocalDataSource == null) {
                val dataFromRemoteDataSource = pokemonApi.getPokemonDetails(id)

                pokemonDetailsDataSource.insert(dataFromRemoteDataSource.toLocalModel())

                Result.success(dataFromRemoteDataSource.toLocalModel().toRepositoryModel())

            } else Result.success(dataFromLocalDataSource.toRepositoryModel())
        }catch (e:Exception) {
            logger.error(e,LOG_TAG)
            Result.failure(e)
        }
    }

    private fun PokemonDetailsRemoteModel.toLocalModel() : PokemonDetailsLocalModel {
        return PokemonDetailsLocalModel(
            id,
            name,
            pokemonImageUrl,
            weight,
            height,
            pokemonTypes.map { it.name }
        )
    }


    private fun PokemonDetailsLocalModel.toRepositoryModel() : PokemonDetails {
        return PokemonDetails(id, name, pokemonImageUrl, weight, height, typesName)
    }

    companion object {
        private const val LOG_TAG = "PokemonDetailsRepositoryImpl"
    }
}