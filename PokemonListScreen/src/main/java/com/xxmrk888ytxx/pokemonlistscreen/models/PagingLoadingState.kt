package com.xxmrk888ytxx.pokemonlistscreen.models

import androidx.annotation.IdRes

sealed class PagingLoadingState {

    data object Loading : PagingLoadingState()

    data object Waiting : PagingLoadingState()

    data object Finished : PagingLoadingState()

    data class Error(
        @IdRes val errorReason:Int
    ): PagingLoadingState()
}
