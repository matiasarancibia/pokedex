package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.StatsItemData
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import javax.inject.Inject

class StatsItemVDMapper @Inject constructor(
    private val statsVDMapper: StatsVDMapper
) : Mapper<StatsItemData?, StatsItemViewData?> {

    override fun executeMapping(data: StatsItemData?): StatsItemViewData? {
        return data?.let {
            StatsItemViewData(
                stat = statsVDMapper.executeMapping(data.statData),
                baseStat = data.baseStat.orElse(0),
                effort = data.effort.orElse(0)
            )
        }
    }
}