package com.xxmrk888ytxx.pokemonlistscreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent

sealed class LocalUiEvent : UiEvent {
    data object RetryFirstLoading : LocalUiEvent()

    data object LoadNextPage : LocalUiEvent()

}
