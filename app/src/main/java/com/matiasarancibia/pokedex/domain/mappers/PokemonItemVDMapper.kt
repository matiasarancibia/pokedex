package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonItemData
import com.matiasarancibia.pokedex.domain.model.PokemonItemViewData
import javax.inject.Inject

class PokemonItemVDMapper @Inject constructor() : Mapper<PokemonItemData?, PokemonItemViewData?> {

    override fun executeMapping(data: PokemonItemData?): PokemonItemViewData? {
        return data?.let {
            PokemonItemViewData(
                name = data.name.orEmpty(),
                url = data.url.orEmpty()
            )
        }
    }
}