package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.FlavorTextEntryItemData
import com.matiasarancibia.pokedex.domain.model.FlavorTextEntryItemViewData
import javax.inject.Inject

class FlavorTextEntryItemVDMapper @Inject constructor(
    private val languageVDMapper: LanguageVDMapper,
    private val versionVDMapper: VersionVDMapper
) : Mapper<FlavorTextEntryItemData?, FlavorTextEntryItemViewData?> {

    override fun executeMapping(data: FlavorTextEntryItemData?): FlavorTextEntryItemViewData? {
        return data?.let {
            FlavorTextEntryItemViewData(
                flavorText = it.flavorText,
                language = languageVDMapper.executeMapping(it.language),
                version = versionVDMapper.executeMapping(it.version)
            )
        }
    }
}