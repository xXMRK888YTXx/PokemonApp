package com.xxmrk888ytxx.database.entityes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "PokemonListItems"
)
internal data class PokemonListItemEntity(
    @PrimaryKey val id:Int,
    val name:String
)
