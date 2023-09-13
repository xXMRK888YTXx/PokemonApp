package com.xxmrk888ytxx.pokemonlistscreen

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.corecompose.LocalNavigator
import com.xxmrk888ytxx.pokemonlistscreen.models.FirstDataLoadingResult
import com.xxmrk888ytxx.pokemonlistscreen.models.LocalUiEvent
import com.xxmrk888ytxx.pokemonlistscreen.models.PagingLoadingState
import com.xxmrk888ytxx.pokemonlistscreen.models.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceType")
@Composable
fun PokemonListScreen(
    screenState:ScreenState,
    onEvent:(UiEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val navigator = LocalNavigator.current


    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.pokemon_app),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->

        AnimatedContent(
            targetState = screenState.firstDataLoadingResult, label = ""
        ) {
            when(it) {
                is FirstDataLoadingResult.Error -> {

                    ErrorForm(
                        text = it.errorReason,
                        onRetry = { onEvent(LocalUiEvent.RetryFirstLoading) }
                    )
                }


                FirstDataLoadingResult.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }


                FirstDataLoadingResult.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        itemsIndexed(screenState.pokemonList,key = { _,it -> it.id }) { index,item ->

                            if(index == screenState.pokemonList.lastIndex && screenState.dataPagingLoadingState is PagingLoadingState.Waiting) {
                                onEvent(LocalUiEvent.LoadNextPage)
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable { onEvent(LocalUiEvent.RequestDetails(navigator,item.id)) }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = item.name,
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }
                            }
                        }

                        if(screenState.dataPagingLoadingState is PagingLoadingState.Loading) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        if(screenState.dataPagingLoadingState is PagingLoadingState.Error) {
                            item {
                                ErrorForm(
                                    text = screenState.dataPagingLoadingState.errorReason,
                                    onRetry = { onEvent(LocalUiEvent.LoadNextPage) }
                                )
                            }
                        }
                    }
                }
            }

        }


    }
}

@SuppressLint("ResourceType")
@Composable
fun ErrorForm(
    @IdRes text:Int,
    onRetry:() -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp,Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.an_error_has_occurred),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.titleMedium
        )

        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}