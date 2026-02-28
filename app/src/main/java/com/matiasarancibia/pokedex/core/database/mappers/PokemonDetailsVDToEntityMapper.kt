package com.matiasarancibia.pokedex.core.database.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class PokemonDetailsVDToEntityMapper @Inject constructor() : Mapper<PokemonDetailsViewData?, PokemonDetailsEntity?> {

    override fun executeMapping(data: PokemonDetailsViewData?): PokemonDetailsEntity? {
        return data?.let {
            PokemonDetailsEntity(
                name = it.name,
                number = it.number.orElse(0),
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