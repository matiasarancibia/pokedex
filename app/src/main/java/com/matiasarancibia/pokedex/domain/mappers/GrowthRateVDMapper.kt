package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.GrowthRateData
import com.matiasarancibia.pokedex.domain.model.GrowthRateViewData
import javax.inject.Inject

class GrowthRateVDMapper @Inject constructor() : Mapper<GrowthRateData?, GrowthRateViewData?> {

    override fun executeMapping(data: GrowthRateData?): GrowthRateViewData? {
        return data?.let {
            GrowthRateViewData(
                name = it.name,
                url = it.url
            )
        }
    }
}