package com.xxmrk888ytxx.pokemondetailsscreen

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.corecompose.LocalNavigator
import com.xxmrk888ytxx.pokemondetailsscreen.models.Details
import com.xxmrk888ytxx.pokemondetailsscreen.models.LoadingDetailsResult
import com.xxmrk888ytxx.pokemondetailsscreen.models.LocalUiEvent
import com.xxmrk888ytxx.pokemondetailsscreen.models.ScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    screenState: ScreenState,
    onEvent:(UiEvent) -> Unit
) {

    val navigator = LocalNavigator.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    if(screenState.loadingState is LoadingDetailsResult.Loaded) {
                        Text(
                            text = screenState.loadingState.details.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onEvent(LocalUiEvent.BackScreen(navigator)) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddings ->

        AnimatedContent(
            targetState = screenState.loadingState,
            label = "",
            modifier = Modifier.padding(paddings)
        ) {
            when(it) {
                is LoadingDetailsResult.Error -> {
                    ErrorForm(text = it.errorReason) {
                        onEvent(LocalUiEvent.RetryLoadingDetails)
                    }
                }
                is LoadingDetailsResult.Loaded -> {
                    PokemonDetailsForm(it.details)
                }

                LoadingDetailsResult.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }


    }
}

@Composable
fun PokemonDetailsForm(details: Details) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        @Composable
        fun InfoCard(text:String) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }

        AsyncImage(
            model = details.pokemonImageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally),
        )

        InfoCard(text = stringResource(R.string.pokemon_s_name, details.name))

        InfoCard(text = stringResource(R.string.pokemon_s_height, details.height))

        InfoCard(text = stringResource(R.string.pokemon_s_weight, details.weight))

        Text(
            text = stringResource(R.string.pokemon_s_types),
            style = MaterialTheme.typography.titleLarge
        )

        details.typesName.forEach {
            InfoCard(text = it)
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
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
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