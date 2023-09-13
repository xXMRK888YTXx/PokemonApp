package com.xxmrk888ytxx.pokemonapi.models

data class PokemonDetailsRemoteModel(
    val id:Int,
    val name:String,
    val pokemonImageUrl:String,
    val weight:Int,
    val height:Int,
    val pokemonTypes:List<PokemonType>
)
