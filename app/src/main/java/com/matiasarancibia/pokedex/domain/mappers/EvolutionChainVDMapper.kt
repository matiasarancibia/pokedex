package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.EvolutionChainData
import com.matiasarancibia.pokedex.domain.model.EvolutionChainViewData
import javax.inject.Inject

class EvolutionChainVDMapper @Inject constructor() : Mapper<EvolutionChainData?, EvolutionChainViewData?> {

    override fun executeMapping(data: EvolutionChainData?): EvolutionChainViewData? {
        return data?.let {
            EvolutionChainViewData(
                url = it.url
            )
        }
    }
}