package com.xxmrk888ytxx.pokemonlistscreen.contracts

import com.xxmrk888ytxx.pokemonlistscreen.exceptions.NoInternetConnection
import com.xxmrk888ytxx.pokemonlistscreen.models.PokemonModel
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

interface ProvidePokemonContract {

    val pokemons: Flow<List<PokemonModel>>

    val isLoading:Flow<Boolean>

    /**
     * Must return true if data for loading is over else false
     */
    @Throws(NoInternetConnection::class)
    suspend fun loadNext() : Result<Boolean>
}