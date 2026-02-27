package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesViewData
import javax.inject.Inject

class PokemonSpeciesVDMapper @Inject constructor() : Mapper<PokemonSpeciesData?, PokemonSpeciesViewData?> {

    override fun executeMapping(data: PokemonSpeciesData?): PokemonSpeciesViewData? {
        return data?.let {
            PokemonSpeciesViewData(
                name = it.name,
                url = it.url
            )
        }
    }
}