package com.xxmrk888ytxx.securespace.presentation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xxmrk888ytxx.goals.extensions.composeViewModel
import com.xxmrk888ytxx.pokemondetailsscreen.PokemonDetailsScreen
import com.xxmrk888ytxx.pokemondetailsscreen.PokemonDetailsViewModel
import com.xxmrk888ytxx.pokemonlistscreen.PokemonListScreen
import com.xxmrk888ytxx.pokemonlistscreen.PokemonListViewModel
import com.xxmrk888ytxx.pokemons.presentation.NavigationKeys
import javax.inject.Provider


fun NavGraphBuilder.pokemonListScreen(
    pokemonListViewModel: Provider<PokemonListViewModel>
) {
    composable(Screen.PokemonListScreen.route) {
        val viewModel = composeViewModel {
            pokemonListViewModel.get()
        }

        val state by viewModel.state.collectAsStateWithLifecycle(
            initialValue = viewModel.defValue
        )

        PokemonListScreen(
            screenState = state,
            onEvent = viewModel::handleEvent
        )
    }
}

fun NavGraphBuilder.pokemonDetailsScreen(
    pokemonDetailsViewModel: PokemonDetailsViewModel.Factory
) {
    composable(
        route = "${Screen.PokemonDetailsScreen.route}/{${NavigationKeys.PokemonIdForPokemonDetailsScreen.key}}",
        arguments = listOf(
            navArgument(NavigationKeys.PokemonIdForPokemonDetailsScreen.key) {
                type = NavType.IntType
                defaultValue = Int.MIN_VALUE
            }
        )
    ) {
        val pokemonId = it.arguments?.getInt(NavigationKeys.PokemonIdForPokemonDetailsScreen.key,Int.MIN_VALUE) ?: return@composable

        val viewModel = composeViewModel {
            pokemonDetailsViewModel.create(pokemonId)
        }

        val state by viewModel.state.collectAsStateWithLifecycle(initialValue = viewModel.defValue)

        PokemonDetailsScreen(
            screenState = state,
            onEvent = viewModel::handleEvent
        )
    }
}