package com.xxmrk888ytxx.pokemons.glue.PokemonDetailsScreen

import com.xxmrk888ytxx.pokemonapi.exceptions.NoInformationException
import com.xxmrk888ytxx.pokemondetailsscreen.contacts.ProvidePokemonDetailsContract
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.DetailsNotFoundException
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.NoInternetException
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.UnknownException
import com.xxmrk888ytxx.pokemondetailsscreen.models.Details
import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.PokemonDetailsRepository
import com.xxmrk888ytxx.pokemons.domain.PokemonDetailsRepository.models.PokemonDetails
import java.net.UnknownHostException
import javax.inject.Inject

class ProvidePokemonDetailsContractImpl @Inject constructor(
    private val pokemonDetailsRepository: PokemonDetailsRepository
) : ProvidePokemonDetailsContract {

    override suspend fun getDetails(id: Int): Result<Details> {
        val result = pokemonDetailsRepository.getDetails(id)

        return if(result.isSuccess) {
            result.map { it.toDetails() }
        } else {
            val exception =  when(result.exceptionOrNull()) {
                is UnknownHostException -> NoInternetException()

                is NoInformationException -> DetailsNotFoundException()

                else -> UnknownException()
            }

            Result.failure(exception)
        }
    }

    private fun PokemonDetails.toDetails() : Details {
        return Details(id, name, pokemonImageUrl, weight, height, typesName)
    }
}