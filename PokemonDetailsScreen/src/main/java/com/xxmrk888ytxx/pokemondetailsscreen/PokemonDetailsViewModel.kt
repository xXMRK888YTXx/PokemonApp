package com.xxmrk888ytxx.pokemondetailsscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.pokemondetailsscreen.models.ScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PokemonDetailsViewModel @AssistedInject constructor(
    @Assisted private val pokemonId:Int
) : ViewModel(),UiModel<ScreenState> {

    override fun handleEvent(event: UiEvent) {

    }

    override val state: Flow<ScreenState> = flowOf(ScreenState())


    private var cashedState = ScreenState()

    override val defValue: ScreenState
        get() = cashedState


    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int) : PokemonDetailsViewModel
    }
}