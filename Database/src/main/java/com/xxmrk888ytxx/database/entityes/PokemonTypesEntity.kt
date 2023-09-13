package com.xxmrk888ytxx.database.entityes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index("pokemonId")],
    tableName = "PokemonTypes",
    foreignKeys = [
        ForeignKey(
            entity = PokemonDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class PokemonTypesEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val pokemonId:Int,
    val typeName:String
)