package com.xxmrk888ytxx.pokemonapi.internalModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListItemResponse(
    @SerialName("name") val name:String,
    @SerialName("url") val url:String
)
