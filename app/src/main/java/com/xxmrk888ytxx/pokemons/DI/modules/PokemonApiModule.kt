package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.pokemonapi.PokemonApi
import com.xxmrk888ytxx.pokemonapi.PokemonApiFactory
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class PokemonApiModule {

    @Provides
    @AppScope
    fun providePokemonApi() : PokemonApi {
        return PokemonApiFactory().create()
    }
}