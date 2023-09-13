package com.xxmrk888ytxx.pokemonlistscreen.models

data class ScreenState(
    val pokemonList:List<PokemonModel> = emptyList(),
    val firstDataLoadingResult: FirstDataLoadingResult = FirstDataLoadingResult.Loading,
    val dataPagingLoadingState:PagingLoadingState = PagingLoadingState.Waiting,
)