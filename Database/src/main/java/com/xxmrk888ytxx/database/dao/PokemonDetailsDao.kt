package com.xxmrk888ytxx.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xxmrk888ytxx.database.entityes.PokemonDetailsEntity

@Dao
interface PokemonDetailsDao {

    @Query("SELECT * FROM PokemonDetails WHERE id = :id")
    suspend fun getPokemonDetailsById(id:Int) : PokemonDetailsEntity?

    @Insert
    suspend fun insert(pokemonDetailsEntity: PokemonDetailsEntity)
}