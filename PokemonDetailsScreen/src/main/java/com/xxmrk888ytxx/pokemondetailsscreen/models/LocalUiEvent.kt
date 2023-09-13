package com.xxmrk888ytxx.pokemondetailsscreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator

sealed class LocalUiEvent : UiEvent {
    data object RetryLoadingDetails : LocalUiEvent()

    class BackScreen(val navigator: Navigator) : LocalUiEvent()
}
