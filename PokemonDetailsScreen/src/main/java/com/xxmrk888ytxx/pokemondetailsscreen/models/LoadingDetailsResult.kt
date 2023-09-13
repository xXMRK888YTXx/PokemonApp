package com.xxmrk888ytxx.pokemondetailsscreen.models

import androidx.annotation.IdRes

sealed class LoadingDetailsResult() {

    data object Loading : LoadingDetailsResult()

    data class Loaded(val details: Details) : LoadingDetailsResult()

    data class Error(
        @IdRes val errorReason:Int
    ) : LoadingDetailsResult()
}
