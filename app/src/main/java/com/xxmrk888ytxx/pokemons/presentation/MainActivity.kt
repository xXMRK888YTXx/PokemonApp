package com.xxmrk888ytxx.pokemons.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.goals.extensions.appComponent
import com.xxmrk888ytxx.goals.extensions.setContentWithTheme
import com.xxmrk888ytxx.pokemonlistscreen.PokemonListViewModel
import com.xxmrk888ytxx.securespace.presentation.ActivityViewModel
import com.xxmrk888ytxx.securespace.presentation.NavigationHost
import com.xxmrk888ytxx.securespace.presentation.Screen
import com.xxmrk888ytxx.securespace.presentation.pokemonListScreen
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var activityViewModelFactory: ActivityViewModel.Factory

    @Inject
    lateinit var pokemonListViewModel: Provider<PokemonListViewModel>

    private val activityViewModel by viewModels<ActivityViewModel> { activityViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        setContentWithTheme {
            val navController = rememberNavController()

            NavigationHost(
                navController = navController,
                navigator = activityViewModel,
                startDestination = Screen.PokemonListScreen.route
            ) {
                pokemonListScreen(pokemonListViewModel)
            }
        }
    }
}