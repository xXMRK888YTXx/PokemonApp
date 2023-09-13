package com.xxmrk888ytxx.pokemondetailsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.coreandroid.getWithCast
import com.xxmrk888ytxx.pokemondetailsscreen.contacts.ProvidePokemonDetailsContract
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.DetailsNotFoundException
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.NoInternetException
import com.xxmrk888ytxx.pokemondetailsscreen.models.LoadingDetailsResult
import com.xxmrk888ytxx.pokemondetailsscreen.models.LocalUiEvent
import com.xxmrk888ytxx.pokemondetailsscreen.models.ScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailsViewModel @AssistedInject constructor(
    @Assisted private val pokemonId:Int,
    private val providePokemonDetailsContract: ProvidePokemonDetailsContract
) : ViewModel(),UiModel<ScreenState> {

    override fun handleEvent(event: UiEvent) {
        if(event !is LocalUiEvent) return

        when(event) {
            LocalUiEvent.RetryLoadingDetails -> {
                loadDetails()
            }

            is LocalUiEvent.BackScreen -> {
                event.navigator.backScreen()
            }
        }
    }
    
    private val loadingDetailsResult = MutableStateFlow<LoadingDetailsResult>(LoadingDetailsResult.Loading)

    override val state: Flow<ScreenState> = combine(loadingDetailsResult) { flowArray:Array<Any> ->
        ScreenState(
            loadingState = flowArray.getWithCast(0)
        ).also { cashedState = it }
    }


    private var cashedState = ScreenState()

    override val defValue: ScreenState
        get() = cashedState
    
    private fun loadDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingDetailsResult.update { LoadingDetailsResult.Loading }

            providePokemonDetailsContract.getDetails(pokemonId)
                .onSuccess { details ->
                    loadingDetailsResult.update { LoadingDetailsResult.Loaded(details) }
                }
                .onFailure { e ->
                    loadingDetailsResult.update { LoadingDetailsResult.Error(exceptionToErrorReason(e)) }
                }
        }
    }

    
    private fun exceptionToErrorReason(e:Throwable) : Int {
        return when(e) {
            is DetailsNotFoundException -> R.string.details_not_found
            
            is NoInternetException -> R.string.no_internet_connection
            
            else -> R.string.unknown_error
        }
    }
    
    
    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int) : PokemonDetailsViewModel
    }
    
    init {
        loadDetails()
    }
}