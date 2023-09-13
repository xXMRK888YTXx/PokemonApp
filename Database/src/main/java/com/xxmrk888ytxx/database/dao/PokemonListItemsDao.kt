package com.xxmrk888ytxx.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xxmrk888ytxx.database.entityes.PokemonListItemEntity

@Dao
internal interface PokemonListItemsDao {

    @Query("SELECT * FROM POKEMONLISTITEMS LIMIT :limit OFFSET :offset")
    suspend fun getPokemonListItems(offset:Int,limit:Int) : List<PokemonListItemEntity>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(pokemonListItemEntity: PokemonListItemEntity) : Int
}