package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.NameItemData
import com.matiasarancibia.pokedex.domain.model.NameItemViewData
import javax.inject.Inject

class NameItemVDMapper @Inject constructor(
    private val languageVDMapper: LanguageVDMapper
) : Mapper<NameItemData?, NameItemViewData?> {

    override fun executeMapping(data: NameItemData?): NameItemViewData? {
        return data?.let {
            NameItemViewData(
                language = languageVDMapper.executeMapping(it.language),
                name = it.name
            )
        }
    }
}