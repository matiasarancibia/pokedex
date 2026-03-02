package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.util.PokemonTypes
import com.matiasarancibia.pokedex.ui.util.convertDecimetersToInches
import com.matiasarancibia.pokedex.ui.util.convertHectogramsToPounds
import com.matiasarancibia.pokedex.ui.util.extensions.capitalizeWord
import com.matiasarancibia.pokedex.ui.util.extensions.formatDecimal
import javax.inject.Inject

class PokemonDetailsVDMapper @Inject constructor(
    private val criesVDMapper: CriesVDMapper,
    private val statsItemVDMapper: StatsItemVDMapper
) : Mapper<PokemonDetailsData?, PokemonDetailsViewData?> {

    override fun executeMapping(data: PokemonDetailsData?): PokemonDetailsViewData? {
        return data?.let {
            PokemonDetailsViewData(
                name = it.name,
                number = it.id,
                cries = criesVDMapper.executeMapping(it.criesData),
                types = it.types?.mapNotNull { type ->
                    PokemonTypes.fromTypeName(type?.type?.name)
                }.orEmpty(),
                typeNamesList = it.types?.mapNotNull { typesItem ->
                    typesItem?.type?.name?.capitalizeWord()
                }.orEmpty(),
                officialArtworkImageUrl = it.sprites?.other?.officialArtwork?.frontDefault.orEmpty(),
                shinyImageUrl = it.sprites?.other?.officialArtwork?.frontShiny.orEmpty(),
                frontImageUrl = it.sprites?.frontDefault.orEmpty(),
                backImageUrl = it.sprites?.backDefault.orEmpty(),
                weight = it.weight?.convertHectogramsToPounds().formatDecimal(),
                height = it.height?.convertDecimetersToInches().formatDecimal(),
                baseExperience = it.baseExperience.toString(),
                stats = it.stats?.mapNotNull { statsItem ->
                    statsItemVDMapper.executeMapping(statsItem)
                }.orEmpty()
            )
        }
    }
}