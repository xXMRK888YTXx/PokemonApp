package com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.models

data class PokemonDetails(
    val id:Int,
    val name:String,
    val pokemonImageUrl:String,
    val weight:Int,
    val height:Int,
    val typesName:List<String>
)
