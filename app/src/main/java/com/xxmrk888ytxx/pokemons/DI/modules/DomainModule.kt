package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.PokemonListItemRepository
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.PokemonListItemRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindPokemonListItemRepository(
        pokemonListItemRepositoryImpl:PokemonListItemRepositoryImpl
    ) : PokemonListItemRepository
}