package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import com.matiasarancibia.pokedex.domain.model.PokemonSpeciesSectionViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orFalse
import javax.inject.Inject

class PokemonSpeciesSectionVDMapper @Inject constructor(
    private val evolutionChainVDMapper: EvolutionChainVDMapper,
    private val flavorTextEntryItemVDMapper: FlavorTextEntryItemVDMapper,
    private val nameItemVDMapper: NameItemVDMapper,
    private val growthRateVDMapper: GrowthRateVDMapper,
    private val generationVDMapper: GenerationVDMapper
) : Mapper<PokemonSpeciesSectionData?, PokemonSpeciesSectionViewData?> {

    override fun executeMapping(data: PokemonSpeciesSectionData?): PokemonSpeciesSectionViewData? {
        return data?.let {
            PokemonSpeciesSectionViewData(
                baseHappiness = it.baseHappiness,
                captureRate = it.captureRate,
                evolutionChain = evolutionChainVDMapper.executeMapping(it.evolutionChain),
                flavorTextEntries = it.flavorTextEntries?.mapNotNull { flavorTextEntry ->
                    flavorTextEntryItemVDMapper.executeMapping(flavorTextEntry)
                },
                names = it.names?.mapNotNull { nameItem ->
                    nameItemVDMapper.executeMapping(nameItem)
                },
                generation = generationVDMapper.executeMapping(it.generation),
                growthRate = growthRateVDMapper.executeMapping(it.growthRate),
                hasGenderDifferences = it.hasGenderDifferences.orFalse(),
                isBaby = it.isBaby.orFalse(),
                isLegendary = it.isLegendary.orFalse(),
                isMythical = it.isMythical.orFalse()
            )
        }
    }
}