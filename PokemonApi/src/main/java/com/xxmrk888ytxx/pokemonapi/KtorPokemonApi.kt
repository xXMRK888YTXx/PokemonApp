package com.xxmrk888ytxx.pokemonapi

import com.xxmrk888ytxx.pokemonapi.exceptions.NoInformationException
import com.xxmrk888ytxx.pokemonapi.internalModels.ListResponse
import com.xxmrk888ytxx.pokemonapi.internalModels.PokemonDetailsResponse
import com.xxmrk888ytxx.pokemonapi.models.PokemonDetailsRemoteModel
import com.xxmrk888ytxx.pokemonapi.models.PokemonListItemRemoteModel
import com.xxmrk888ytxx.pokemonapi.models.PokemonType
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    override suspend fun getPokemonList(offset: Int, limit: Int): List<PokemonListItemRemoteModel> = withContext(Dispatchers.IO) {
        val url = "https://pokeapi.co/api/v2/pokemon?offset=$offset&limit=$limit"

        val response = json.decodeFromString<ListResponse>(httpClient.get(url).bodyAsText())



        return@withContext response.results.map {
            PokemonListItemRemoteModel(
                remoteId = getRemoteIdByDetailsUrl(it.url),
                name = it.name,
            )
        }
    }

    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetailsRemoteModel {
        val url = "https://pokeapi.co/api/v2/pokemon/$pokemonId/"

        val response = httpClient.get(url)

        when(response.status.value) {
            404 -> throw NoInformationException()
        }

        val responseModel =  json.decodeFromString<PokemonDetailsResponse>(httpClient.get(url).bodyAsText())

        return PokemonDetailsRemoteModel(
            id = responseModel.id,
            name = responseModel.name,
            pokemonImageUrl = responseModel.sprites.frontDefault,
            weight = responseModel.weight,
            height = responseModel.height,
            pokemonTypes = responseModel.types.type.map { PokemonType(it.name) }
        )
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