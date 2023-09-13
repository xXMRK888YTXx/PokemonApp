package com.xxmrk888ytxx.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmrk888ytxx.database.dao.PokemonDetailsDao
import com.xxmrk888ytxx.database.dao.PokemonListItemsDao
import com.xxmrk888ytxx.database.dao.PokemonTypesDao
import com.xxmrk888ytxx.database.entityes.PokemonDetailsEntity
import com.xxmrk888ytxx.database.entityes.PokemonListItemEntity
import com.xxmrk888ytxx.database.entityes.PokemonTypesEntity

@Database(
    version = 1,
    entities = [PokemonListItemEntity::class, PokemonDetailsEntity::class,PokemonTypesEntity::class,]
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract val pokemonListItemsDao:PokemonListItemsDao

    abstract val pokemonDetailsDao:PokemonDetailsDao

    abstract val pokemonTypesDao:PokemonTypesDao
}