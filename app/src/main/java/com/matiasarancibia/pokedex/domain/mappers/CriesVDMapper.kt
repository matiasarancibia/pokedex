package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.CriesData
import com.matiasarancibia.pokedex.domain.model.CriesViewData
import javax.inject.Inject

class CriesVDMapper @Inject constructor() : Mapper<CriesData?, CriesViewData?> {

    override fun executeMapping(data: CriesData?): CriesViewData? {
        return data?.let {
            CriesViewData(
                legacy = it.legacy,
                latest = it.latest
            )
        }
    }
}