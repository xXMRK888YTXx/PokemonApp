package com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource

import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.exceptions.LoadingInProcessException
import com.xxmrk888ytxx.pokemons.domain.PagingPokemonsDataSource.models.PagingLoadingResult
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.PokemonListItemRepository
import com.xxmrk888ytxx.pokemons.domain.PokemonListItemRepository.models.PokemonItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PagingPokemonsDataSourceImpl @Inject constructor(
    private val pokemonListItemRepository: PokemonListItemRepository
) : PagingPokemonsDataSource {

    private val pageLimit = 20

    private var currentPage = 0

    private val _pokemons = MutableStateFlow<List<PokemonItem>>(emptyList())

    private val _isDataLoading = MutableStateFlow(false)

    override val pokemons: Flow<List<PokemonItem>> = _pokemons.asStateFlow()

    override val isDataLoading: Flow<Boolean> = _isDataLoading.asStateFlow()

    private val mutex = Mutex()

    override suspend fun loadNextPage(): Result<PagingLoadingResult> = withContext(Dispatchers.IO) {
        mutex.withLock {
            if(_isDataLoading.value) return@withContext Result.failure(LoadingInProcessException())

            try {
                _isDataLoading.update { true }

                val items = pokemonListItemRepository.getPokemonList(currentPage * pageLimit,pageLimit)

                val isAllDataLoaded = items.isEmpty()

                if(!isAllDataLoaded) {
                    currentPage += 1

                    _pokemons.update { oldData -> oldData + items }
                }

                return@withContext Result.success(PagingLoadingResult(isAllDataLoaded))
            }catch (e:Exception) {
                return@withContext Result.failure(e)
            } finally {
                withContext(NonCancellable) {
                    _isDataLoading.update { false }
                }
            }
        }
    }

    override suspend fun reset() = mutex.withLock {
        _pokemons.update { emptyList() }

        currentPage = 0
    }
}