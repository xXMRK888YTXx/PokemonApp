package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.PagingPokemonsDataSource
import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.PagingPokemonsDataSourceImpl
import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.PokemonDetailsRepository
import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.PokemonDetailsRepositoryImpl
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

    @Binds
    fun bindPagingPokemonsDataSource(
        pagingPokemonsDataSourceImpl: PagingPokemonsDataSourceImpl
    ) : PagingPokemonsDataSource

    @Binds
    fun bindPokemonDetailsRepository(
        pokemonDetailsRepositoryImpl: PokemonDetailsRepositoryImpl
    ) : PokemonDetailsRepository
}