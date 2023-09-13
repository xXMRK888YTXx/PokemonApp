package com.xxmrk888ytxx.pokemonapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

class PokemonApiFactory {

    private val httpClient by lazy {
        val json = kotlinx.serialization.json.Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }

        HttpClient(Android) {

            engine {
                connectTimeout = 3000
                socketTimeout = 3000
            }

            install(ContentNegotiation) {
                json(
                    json = json
                )
            }

            install(Logging)

            addDefaultResponseValidation()
        }
    }

    fun create() : PokemonApi {
        return KtorPokemonApi(httpClient)
    }
}