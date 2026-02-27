package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokemonEntryItemData
import com.matiasarancibia.pokedex.domain.model.PokemonEntryItemViewData
import javax.inject.Inject

class PokemonEntryItemVDMapper @Inject constructor(
    private val pokemonSpeciesVDMapper: PokemonSpeciesVDMapper
) : Mapper<PokemonEntryItemData?, PokemonEntryItemViewData?> {

    override fun executeMapping(data: PokemonEntryItemData?): PokemonEntryItemViewData? {
        return data?.let {
            PokemonEntryItemViewData(
                entryNumber = it.entryNumber,
                pokemonSpecies = pokemonSpeciesVDMapper.executeMapping(it.pokemonSpecies)
            )
        }
    }
}