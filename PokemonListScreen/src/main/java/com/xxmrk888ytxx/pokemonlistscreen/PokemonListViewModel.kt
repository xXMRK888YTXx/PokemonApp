package com.xxmrk888ytxx.pokemonlistscreen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.coreandroid.getWithCast
import com.xxmrk888ytxx.pokemonlistscreen.contracts.ProvidePokemonContract
import com.xxmrk888ytxx.pokemonlistscreen.exceptions.NoInternetConnection
import com.xxmrk888ytxx.pokemonlistscreen.models.FirstDataLoadingResult
import com.xxmrk888ytxx.pokemonlistscreen.models.LocalUiEvent
import com.xxmrk888ytxx.pokemonlistscreen.models.PagingLoadingState
import com.xxmrk888ytxx.pokemonlistscreen.models.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val providePokemonContract: ProvidePokemonContract
) : ViewModel(),UiModel<ScreenState> {

    override fun handleEvent(event: UiEvent) {
        if(event !is LocalUiEvent) return

        when(event) {
            LocalUiEvent.LoadNextPage -> nextPage()

            LocalUiEvent.RetryFirstLoading -> firstDataLoading()

            is LocalUiEvent.RequestDetails -> {
                event.navigator.toPokemonDetailsScreen(event.id)
            }
        }
    }

    private val firstDataLoadingResult = MutableStateFlow<FirstDataLoadingResult>(FirstDataLoadingResult.Loading)

    private val pagingLoadingState:MutableStateFlow<PagingLoadingState> = MutableStateFlow(PagingLoadingState.Waiting)

    override val state: Flow<ScreenState> = combine(
        providePokemonContract.pokemons,firstDataLoadingResult,pagingLoadingState
    ) { flowArray ->
        ScreenState(
            pokemonList = flowArray.getWithCast(0),
            firstDataLoadingResult = flowArray.getWithCast(1),
            dataPagingLoadingState = flowArray.getWithCast(2)
        ).also { cashedState = it }
    }

    private var cashedState = ScreenState()

    override val defValue: ScreenState
        get() = cashedState

    @SuppressLint("ResourceType")
    private fun nextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if(providePokemonContract.isLoading.first() || pagingLoadingState.value is PagingLoadingState.Finished) return@launch

            pagingLoadingState.update { PagingLoadingState.Loading }

            providePokemonContract.loadNext()
                .onSuccess { isAllDataLoading ->
                    if(isAllDataLoading) {
                        pagingLoadingState.update { PagingLoadingState.Finished }
                    } else {
                        pagingLoadingState.update { PagingLoadingState.Waiting }
                    }
                }
                .onFailure { e ->
                    pagingLoadingState.update { PagingLoadingState.Error(exceptionToErrorReason(e)) }
                }
        }
    }
    
    @SuppressLint("ResourceType")
    private fun firstDataLoading() {
        viewModelScope.launch(Dispatchers.IO) {
            if(providePokemonContract.isLoading.first()) return@launch

            firstDataLoadingResult.update { FirstDataLoadingResult.Loading }
            providePokemonContract.loadNext()
                .onSuccess { isAllDataLoaded ->
                    if(isAllDataLoaded) {
                        firstDataLoadingResult.update { FirstDataLoadingResult.Error(R.string.data_is_empty) }
                    } else {
                        firstDataLoadingResult.update { FirstDataLoadingResult.Success }
                    }
                }
                .onFailure { e ->
                    firstDataLoadingResult.update { FirstDataLoadingResult.Error(exceptionToErrorReason(e)) }
                }
        }
    }
    
    private fun exceptionToErrorReason(exception: Throwable) : Int {
        return when(exception) {
            is NoInternetConnection -> R.string.no_internet_connection

            else -> R.string.unknown_error
        }
    } 

    init {
        firstDataLoading()
    }


}