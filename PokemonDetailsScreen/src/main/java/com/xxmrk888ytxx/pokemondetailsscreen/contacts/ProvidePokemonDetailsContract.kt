package com.xxmrk888ytxx.pokemondetailsscreen.contacts

import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.DetailsNotFoundException
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.NoInternetException
import com.xxmrk888ytxx.pokemondetailsscreen.exceptions.UnknownException
import com.xxmrk888ytxx.pokemondetailsscreen.models.Details
import kotlin.jvm.Throws

interface ProvidePokemonDetailsContract {

    @Throws(DetailsNotFoundException::class,NoInternetException::class, UnknownException::class)
    suspend fun getDetails(id:Int) : Result<Details>
}