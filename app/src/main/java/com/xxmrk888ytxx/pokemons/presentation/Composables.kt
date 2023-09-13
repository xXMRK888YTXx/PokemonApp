package com.xxmrk888ytxx.securespace.presentation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.xxmrk888ytxx.goals.extensions.composeViewModel
import com.xxmrk888ytxx.pokemonlistscreen.PokemonListScreen
import com.xxmrk888ytxx.pokemonlistscreen.PokemonListViewModel
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