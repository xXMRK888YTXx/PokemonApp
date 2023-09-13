package com.xxmrk888ytxx.pokemonlistscreen.models

import com.xxmrk888ytxx.coreandroid.ShareInterfaces.MVI.UiEvent
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator

sealed class LocalUiEvent : UiEvent {
    data object RetryFirstLoading : LocalUiEvent()

    data object LoadNextPage : LocalUiEvent()

    class RequestDetails(val navigator: Navigator,val id:Int) : LocalUiEvent()

}
