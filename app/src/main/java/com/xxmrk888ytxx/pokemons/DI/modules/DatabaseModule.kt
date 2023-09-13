package com.xxmrk888ytxx.pokemons.DI.modules

import android.content.Context
import com.xxmrk888ytxx.database.Database
import com.xxmrk888ytxx.database.dataSources.PokemonDetailsDataSource
import com.xxmrk888ytxx.database.dataSources.PokemonListDataSource
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @AppScope
    fun bindDatabase(context: Context) : Database {
        return Database.create(context)
    }

    @Provides
    fun providePokemonListDataSource(database: Database) : PokemonListDataSource {
        return database.pokemonListDataSource
    }

    @Provides
    fun providesPokemonDetailsDataSource(database: Database) : PokemonDetailsDataSource {
        return database.pokemonDetailsDataSource
    }
}