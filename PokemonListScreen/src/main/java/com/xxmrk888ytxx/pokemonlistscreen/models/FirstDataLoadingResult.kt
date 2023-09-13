package com.xxmrk888ytxx.pokemonlistscreen.models

import androidx.annotation.IdRes

sealed class FirstDataLoadingResult {
    data object Loading : FirstDataLoadingResult()

    data object Success : FirstDataLoadingResult()

    data class Error(@IdRes val errorReason: Int) : FirstDataLoadingResult()
}
