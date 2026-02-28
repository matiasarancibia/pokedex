package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import javax.inject.Inject

class PokemonDetailsEntityToVDMapper @Inject constructor() : Mapper<PokemonDetailsEntity?, PokemonDetailsViewData?> {

    override fun executeMapping(data: PokemonDetailsEntity?): PokemonDetailsViewData? {
        return data?.let {
            PokemonDetailsViewData(
                name = it.name,
                number = it.number,
                cries = it.cries,
                types = it.types,
                typeNamesList = it.typeNamesList,
                typesUrl = it.typesUrl,
                officialArtworkImageUrl = it.officialArtworkImageUrl,
                shinyImageUrl = it.shinyImageUrl,
                frontImageUrl = it.frontImageUrl,
                backImageUrl = it.backImageUrl,
                weight = it.weight,
                height = it.height,
                baseExperience = it.baseExperience,
                stats = it.stats
            )
        }
    }
}