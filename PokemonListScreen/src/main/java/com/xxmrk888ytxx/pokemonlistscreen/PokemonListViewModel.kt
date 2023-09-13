package com.xxmrk888ytxx.pokemonlistscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiModel
import com.xxmrk888ytxx.pokemonlistscreen.models.ScreenState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(

) : ViewModel(),UiModel<ScreenState> {

    override fun handleEvent(event: UiEvent) {

    }

    override val state: Flow<ScreenState> = flowOf(ScreenState())

    private val cashedState = ScreenState()

    override val defValue: ScreenState
        get() = cashedState


}