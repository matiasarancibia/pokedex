package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.VersionData
import com.matiasarancibia.pokedex.domain.model.VersionViewData
import javax.inject.Inject

class VersionVDMapper @Inject constructor() : Mapper<VersionData?, VersionViewData?> {

    override fun executeMapping(data: VersionData?): VersionViewData? {
        return data?.let {
            VersionViewData(
                name = it.name,
                url = it.url
            )
        }
    }
}