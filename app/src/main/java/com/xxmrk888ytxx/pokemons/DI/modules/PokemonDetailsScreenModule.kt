package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.pokemondetailsscreen.contacts.ProvidePokemonDetailsContract
import com.xxmrk888ytxx.pokemons.glue.PokemonDetailsScreen.ProvidePokemonDetailsContractImpl
import dagger.Binds
import dagger.Module

@Module
interface PokemonDetailsScreenModule {

    @Binds
    fun bindProvidePokemonDetailsContract(
        providePokemonDetailsContractImpl: ProvidePokemonDetailsContractImpl
    ) : ProvidePokemonDetailsContract
}