package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.PokedexSectionData
import com.matiasarancibia.pokedex.domain.model.PokedexSectionViewData
import javax.inject.Inject

class PokedexVDMapper @Inject constructor(
    private val nameItemVDMapper: NameItemVDMapper,
    private val pokemonEntryItemVDMapper: PokemonEntryItemVDMapper
) : Mapper<PokedexSectionData?, PokedexSectionViewData?> {

    override fun executeMapping(data: PokedexSectionData?): PokedexSectionViewData? {
        return data?.let {
            PokedexSectionViewData(
                id = it.id,
                name = it.name,
                names = it.names?.mapNotNull {
                    nameItemVDMapper.executeMapping(it)
                },
                pokemonEntries = it.pokemonEntries?.mapNotNull {
                    pokemonEntryItemVDMapper.executeMapping(it)
                }
            )
        }
    }
}