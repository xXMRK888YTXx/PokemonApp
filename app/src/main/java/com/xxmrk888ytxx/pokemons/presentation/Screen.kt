package com.xxmrk888ytxx.securespace.presentation

sealed class Screen(val route:String) {

    object PokemonListScreen : Screen("PokemonListScreen")

    object PokemonDetailsScreen : Screen("PokemonDetailsScreen")


}
