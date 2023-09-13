package com.xxmrk888ytxx.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xxmrk888ytxx.database.entityes.PokemonTypesEntity

@Dao
interface PokemonTypesDao {

    @Query("SELECT * FROM PokemonTypes WHERE pokemonId = :pokemonId")
    suspend fun getPokemonTypes(pokemonId:Int) : List<PokemonTypesEntity>

    @Insert
    suspend fun insert(pokemonTypesEntity: PokemonTypesEntity)
}