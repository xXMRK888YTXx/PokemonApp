package com.xxmrk888ytxx.database.models

import androidx.room.PrimaryKey

data class PokemonDetailsLocalModel(
    val id:Int,
    val name:String,
    val pokemonImageUrl:String,
    val weight:Int,
    val height:Int,
    val typesName:List<String>
)
