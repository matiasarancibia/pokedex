package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonListResponseData
import com.matiasarancibia.pokedex.domain.model.PokemonListResponseViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class PokemonListResponseVDMapper @Inject constructor(
    private val pokemonItemVDMapper: PokemonItemVDMapper
) : Mapper<PokemonListResponseData?, PokemonListResponseViewData?> {

    override fun executeMapping(data: PokemonListResponseData?): PokemonListResponseViewData? {
        return data?.let {
            PokemonListResponseViewData(
                count = data.count.orElse(0),
                previous = data.next.orEmpty(),
                next = data.next.orEmpty(),
                results = it.results?.mapNotNull { pokemonItem ->
                    pokemonItemVDMapper.executeMapping(pokemonItem)
                }.orEmpty()
            )
        }
    }
}