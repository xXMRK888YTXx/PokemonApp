package com.xxmrk888ytxx.pokemonapi.internalModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeResponse(
    @SerialName("name") val name:String
)