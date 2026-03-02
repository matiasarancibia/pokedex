package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.SpeciesData
import com.matiasarancibia.pokedex.domain.model.SpeciesViewData
import javax.inject.Inject

class SpeciesVDMapper @Inject constructor() : Mapper<SpeciesData?, SpeciesViewData?> {

    override fun executeMapping(data: SpeciesData?): SpeciesViewData? {
        return data?.let {
            SpeciesViewData(
                name = it.name,
                url = it.url
            )
        }
    }
}