package com.xxmrk888ytxx.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmrk888ytxx.database.dao.PokemonListItemsDao
import com.xxmrk888ytxx.database.entityes.PokemonListItemEntity

@Database(
    version = 1,
    entities = [PokemonListItemEntity::class]
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val pokemonListItemsDao:PokemonListItemsDao
}