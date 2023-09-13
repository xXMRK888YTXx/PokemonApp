package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.pokemonlistscreen.contracts.ProvidePokemonContract
import com.xxmrk888ytxx.pokemons.glue.PokemonListScreen.ProvidePokemonContractImpl
import dagger.Binds
import dagger.Module

@Module
interface PokemonListScreenModule {

    @Binds
    fun bindProvidePokemonContract(
        providePokemonContractImpl:ProvidePokemonContractImpl
    ) : ProvidePokemonContract
}