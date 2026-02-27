package com.matiasarancibia.pokedex.ui.util.helpers

import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.domain.model.CriesViewData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.domain.model.StatsViewData
import com.matiasarancibia.pokedex.ui.util.PokemonTypes
import com.matiasarancibia.pokedex.ui.util.PokemonTypes.FIRE
import com.matiasarancibia.pokedex.ui.util.PokemonTypes.FLYING
import com.matiasarancibia.pokedex.ui.util.PokemonTypes.GRASS
import com.matiasarancibia.pokedex.ui.util.PokemonTypes.POISON
import com.matiasarancibia.pokedex.ui.util.extensions.empty

class PokemonItemHelper {

    companion object {

        fun createStatItemViewData(
            baseStat: Int,
            name: String
        ) = StatsItemViewData(
            baseStat = baseStat,
            effort = 0,
            stat = StatsViewData(
                name = name,
                url = String.empty()
            )
        )

        fun createPokemonDetailsViewData(
            name: String,
            number: Int,
            types: List<PokemonTypes>,
            fakeImage: Int,
        ) = PokemonDetailsViewData(
            name = name,
            number = number,
            cries = CriesViewData(
                latest = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/latest/1.ogg",
                legacy = "https://raw.githubusercontent.com/PokeAPI/cries/main/cries/pokemon/legacy/1.ogg"
            ),
            types = types,
            officialArtworkImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            shinyImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png",
            typeNamesList = types.map { it.typeName },
            typesUrl = listOf("https://pokeapi.co/api/v2/type/12/", "https://pokeapi.co/api/v2/type/4/"),
            fakePokemonImageRes = fakeImage,
            weight = "69",
            height = "7",
            baseExperience = "64",
            stats = listOf(
                createStatItemViewData(baseStat = 30, "HP"),
                createStatItemViewData(baseStat = 35, "ATK"),
                createStatItemViewData(baseStat = 30, "DEF"),
                createStatItemViewData(baseStat = 100, "SATK"),
                createStatItemViewData(baseStat = 35, "SDEF"),
                createStatItemViewData(baseStat = 80, "SPD")
            )
        )

        fun createPokemonDetailsList() = listOf(
            createPokemonDetailsViewData(name = "Bulbasaur", number = 1, types = listOf(GRASS, POISON), fakeImage = R.drawable.bulbasaur),
            createPokemonDetailsViewData(name = "Ivysaur", number = 2, types = listOf(GRASS, POISON), fakeImage = R.drawable.ivysaur),
            createPokemonDetailsViewData(name = "Venasaur", number = 3, types = listOf(GRASS, POISON), fakeImage = R.drawable.venasaur),
            createPokemonDetailsViewData(name = "Charmander", number = 4, types = listOf(FIRE), fakeImage = R.drawable.charmander),
            createPokemonDetailsViewData(name = "Charmeleon", number = 5, types = listOf(FIRE), fakeImage = R.drawable.charmeleon),
            createPokemonDetailsViewData(name = "Charizard", number = 6, types = listOf(FIRE, FLYING), fakeImage = R.drawable.charizard),
        )
    }
}