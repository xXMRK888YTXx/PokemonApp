package com.xxmrk888ytxx.pokemonapi.internalModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SpritesResponse(
    @SerialName("frontDefault") val frontDefault:String
)