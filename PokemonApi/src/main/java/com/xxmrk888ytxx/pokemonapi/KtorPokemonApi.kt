package com.xxmrk888ytxx.pokemonapi

import com.xxmrk888ytxx.pokemonapi.internalModels.ListResponse
import com.xxmrk888ytxx.pokemonapi.models.PokemonListItemRemoteModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.jetbrains.annotations.TestOnly

internal class KtorPokemonApi(
    private val httpClient: HttpClient
) : PokemonApi {

    private val json by lazy {
        kotlinx.serialization.json.Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonListItemRemoteModel> {
        val url = "https://pokeapi.co/api/v2/pokemon?offset=$offset&limit=$limit"

        val response = json.decodeFromString<ListResponse>(httpClient.get(url).bodyAsText())



        return response.results.map {
            PokemonListItemRemoteModel(
                remoteId = getRemoteIdByDetailsUrl(it.url),
                name = it.name,
                detailsUrl = it.url
            )
        }
    }

    @TestOnly
    fun getRemoteIdByDetailsUrl(detailsUrl:String) : Int {
        var currentIndex = detailsUrl.lastIndex - 1
        var output = ""


        while (detailsUrl[currentIndex].isDigit()) {
           output += detailsUrl[currentIndex]

           currentIndex -= 1
        }

        return output.reversed().toInt()
    }
}