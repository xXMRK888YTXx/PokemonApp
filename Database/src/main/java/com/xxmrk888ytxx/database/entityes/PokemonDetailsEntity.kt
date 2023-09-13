package com.xxmrk888ytxx.database.entityes

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "PokemonDetails",
    indices = [Index("id", unique = true)]
)
data class PokemonDetailsEntity(
    @PrimaryKey val id:Int,
    val name:String,
    val pokemonImageUrl:String,
    val weight:Int,
    val height:Int,
)
