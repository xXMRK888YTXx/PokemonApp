package com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository

import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.models.PokemonDetails


interface PokemonDetailsRepository {

    suspend fun getDetails(id:Int) : Result<PokemonDetails>
}