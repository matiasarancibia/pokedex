package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.StatData
import com.matiasarancibia.pokedex.domain.model.StatsViewData
import javax.inject.Inject

class StatsVDMapper @Inject constructor() : Mapper<StatData?, StatsViewData?> {

    override fun executeMapping(data: StatData?): StatsViewData? {
        return data?.let {
            StatsViewData(
                name = formatStatName(data.name.orEmpty()),
                url = data.url
            )
        }
    }

    private fun formatStatName(statName: String) = when {
        statName.contains("hp", ignoreCase = true) -> "HP"
        statName.contains("-attack", ignoreCase = true) -> "SATK"
        statName.contains("-defense", ignoreCase = true) -> "SDEF"
        statName.contains("attack", ignoreCase = true) -> "ATK"
        statName.contains("defense", ignoreCase = true) -> "DEF"
        statName.contains("speed", ignoreCase = true) -> "SPD"
        else -> statName
    }
}