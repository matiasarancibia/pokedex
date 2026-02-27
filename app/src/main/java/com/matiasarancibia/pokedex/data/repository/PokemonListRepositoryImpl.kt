package com.matiasarancibia.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.matiasarancibia.pokedex.domain.mappers.PokemonDetailsVDMapper
import com.matiasarancibia.pokedex.domain.mappers.PokemonItemVDMapper
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.repository.PokedexApiService
import com.matiasarancibia.pokedex.domain.useCase.PokemonPagingSource
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val pokedexApiService: PokedexApiService,
    private val pokemonItemVDMapper: PokemonItemVDMapper,
    private val pokemonDetailsVDMapper: PokemonDetailsVDMapper
) : IPokemonListRepository {

    companion object {
        private const val DEFAULT_LIMIT = 20
        private const val PREFETCH_DISTANCE = 15
    }

    override fun getPokemonStream(
        pageSize: Int?
    ): Flow<PagingData<PokemonDetailsViewData>> {
        val finalPageSize = pageSize.orElse(DEFAULT_LIMIT)

        return Pager(
            config = PagingConfig(
                pageSize = finalPageSize.orElse(DEFAULT_LIMIT),
                initialLoadSize = finalPageSize * 2,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonPagingSource(
                    pokedexApiService,
                    finalPageSize,
                    pokemonItemVDMapper,
                    pokemonDetailsVDMapper
                )
            }
        ).flow
    }
}