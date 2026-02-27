package com.matiasarancibia.pokedex.domain.useCase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matiasarancibia.pokedex.domain.mappers.PokemonDetailsVDMapper
import com.matiasarancibia.pokedex.domain.mappers.PokemonItemVDMapper
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.repository.PokedexApiService
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PokemonPagingSource(
    private val api: PokedexApiService,
    private val pageSize: Int,
    private val pokemonItemVDMapper: PokemonItemVDMapper,
    private val pokemonDetailsVDMapper: PokemonDetailsVDMapper
) : PagingSource<Int, PokemonDetailsViewData>() {

    override fun getRefreshKey(
        state: PagingState<Int, PokemonDetailsViewData>
    ): Int? {
        // This is to keep the user near to their current position after refresh
        val anchor = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchor) ?: return null

        return page.prevKey?.plus(1).orElse(page.nextKey?.minus(1))
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PokemonDetailsViewData> {
        return try {
            val pageIndex = params.key.orElse(0)
            val offset = pageIndex * pageSize
            val limit = params.loadSize.coerceAtMost(pageSize) // Paging may ask for > pageSize on initial

            val page = api.fetchPokemonList(offset = offset, limit = limit)

            // Here we fetch details in parallel for this page
            val items: List<PokemonDetailsViewData> = coroutineScope {
                page.results?.mapNotNull { item ->
                    val itemVD = pokemonItemVDMapper.executeMapping(item)

                    async {
                        val detailsData = api.getPokemonDetailsByUrl(itemVD?.url.orEmpty())
                        val detailsVD = pokemonDetailsVDMapper.executeMapping(detailsData)

                        detailsVD.orElse(PokemonDetailsViewData())
                    }
                }?.awaitAll().orEmpty()
            }

            val nextKey = if (page.next == null) null else pageIndex + 1
            val prevKey = if (pageIndex == 0) null else pageIndex - 1

            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}