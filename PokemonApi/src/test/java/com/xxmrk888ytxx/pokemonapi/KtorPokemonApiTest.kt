package com.xxmrk888ytxx.pokemonapi

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class KtorPokemonApiTest {


    private val api = KtorPokemonApi(mockk())

    private fun getTestDetailsUrl(testRemoveId:Int) = "https://pokeapi.co/api/v2/pokemon/$testRemoveId/"

    @Test
    fun testParseRemoteIdFromDetailsUrlWithOneNumberId() {
        val expectedId = 8
        val parsedId = api.getRemoteIdByDetailsUrl(getTestDetailsUrl(expectedId))

        Assert.assertEquals(expectedId,parsedId)
    }

    @Test
    fun testParseRemoteIdFromDetailsUrlWithTwoNumberId() {
        val expectedId = 81
        val parsedId = api.getRemoteIdByDetailsUrl(getTestDetailsUrl(expectedId))

        Assert.assertEquals(expectedId,parsedId)
    }

    @Test
    fun testParseRemoteIdFromDetailsUrlWithThreeNumberId() {
        val expectedId = 806
        val parsedId = api.getRemoteIdByDetailsUrl(getTestDetailsUrl(expectedId))

        Assert.assertEquals(expectedId,parsedId)
    }
}