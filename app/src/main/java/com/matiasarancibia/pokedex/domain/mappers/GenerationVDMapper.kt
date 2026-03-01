package com.matiasarancibia.pokedex.domain.mappers

import com.matiasarancibia.pokedex.core.common.Mapper
import com.matiasarancibia.pokedex.data.model.GenerationData
import com.matiasarancibia.pokedex.domain.model.GenerationViewData
import com.matiasarancibia.pokedex.ui.util.extensions.capitalizeWord
import javax.inject.Inject

class GenerationVDMapper @Inject constructor() : Mapper<GenerationData?, GenerationViewData?> {

    override fun executeMapping(data: GenerationData?): GenerationViewData? {
        return data?.let {
            GenerationViewData(
                name = it.name?.formatGenerationText(),
                url = it.url
            )
        }
    }

    private fun String.formatGenerationText(): String {
        val generationArray = this.split("-")

        return try {
            val generationTitle = generationArray[0].capitalizeWord()
            val generationNumber = generationArray[1].uppercase()

            return "$generationTitle $generationNumber"
        } catch (_: Exception) {
            this
        }
    }
}
