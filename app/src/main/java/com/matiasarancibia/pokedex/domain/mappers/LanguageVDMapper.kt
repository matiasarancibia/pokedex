package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.LanguageData
import com.matiasarancibia.pokedex.domain.model.LanguageViewData
import javax.inject.Inject

class LanguageVDMapper @Inject constructor() : Mapper<LanguageData?, LanguageViewData?> {

    override fun executeMapping(data: LanguageData?): LanguageViewData? {
        return data?.let {
            LanguageViewData(
                name = it.name,
                url = it.url
            )
        }
    }
}
