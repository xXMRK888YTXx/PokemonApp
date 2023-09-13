package com.xxmrk888ytxx.pokemonapi.internalModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonDetailsResponse(
    @SerialName("id") val id:Int,
    @SerialName("name") val name:String,
    @SerialName("height") val height:Int,
    @SerialName("weight") val weight:Int,
    @SerialName("sprites") val sprites:SpritesResponse,
    @SerialName("types") val types:List<TypesResponse>
)