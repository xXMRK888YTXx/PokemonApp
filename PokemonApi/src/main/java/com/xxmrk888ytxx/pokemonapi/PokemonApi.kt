package com.xxmrk888ytxx.pokemonapi

import com.xxmrk888ytxx.pokemonapi.exceptions.NoInformationException
import com.xxmrk888ytxx.pokemonapi.models.PokemonDetailsRemoteModel
import com.xxmrk888ytxx.pokemonapi.models.PokemonListItemRemoteModel
import java.net.UnknownHostException
import kotlin.jvm.Throws

interface PokemonApi {

    @Throws(UnknownHostException::class)
    suspend fun getPokemonList(offset:Int,limit:Int) : List<PokemonListItemRemoteModel>

    @Throws(NoInformationException::class,UnknownHostException::class)
    suspend fun getPokemonDetails(pokemonId:Int) : PokemonDetailsRemoteModel
}