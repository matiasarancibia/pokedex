package com.matiasarancibia.pokedex.data.repository

import androidx.paging.PagingData
import com.matiasarancibia.pokedex.core.common.BaseRepository
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import kotlinx.coroutines.flow.Flow

interface IPokemonListRepository : BaseRepository {

    fun getPokemonStream(pageSize: Int?): Flow<PagingData<PokemonDetailsViewData>>
}