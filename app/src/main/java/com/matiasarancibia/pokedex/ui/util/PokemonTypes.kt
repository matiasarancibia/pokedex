package com.matiasarancibia.pokedex.ui.util

import androidx.compose.ui.graphics.Color
import com.matiasarancibia.pokedex.ui.theme.BugTypeColor
import com.matiasarancibia.pokedex.ui.theme.DarkTypeColor
import com.matiasarancibia.pokedex.ui.theme.DragonTypeColor
import com.matiasarancibia.pokedex.ui.theme.ElectricTypeColor
import com.matiasarancibia.pokedex.ui.theme.FairyTypeColor
import com.matiasarancibia.pokedex.ui.theme.FightingTypeColor
import com.matiasarancibia.pokedex.ui.theme.FireTypeColor
import com.matiasarancibia.pokedex.ui.theme.FlyingTypeColor
import com.matiasarancibia.pokedex.ui.theme.GhostTypeColor
import com.matiasarancibia.pokedex.ui.theme.GrassTypeColor
import com.matiasarancibia.pokedex.ui.theme.GroundTypeColor
import com.matiasarancibia.pokedex.ui.theme.IceTypeColor
import com.matiasarancibia.pokedex.ui.theme.NormalTypeColor
import com.matiasarancibia.pokedex.ui.theme.PoisonTypeColor
import com.matiasarancibia.pokedex.ui.theme.PsychicTypeColor
import com.matiasarancibia.pokedex.ui.theme.RockTypeColor
import com.matiasarancibia.pokedex.ui.theme.SteelTypeColor
import com.matiasarancibia.pokedex.ui.theme.WaterTypeColor
import com.matiasarancibia.pokedex.ui.util.extensions.orElse

enum class PokemonTypes(
    val typeName: String,
    val color: Color
) {
    NORMAL("Normal", NormalTypeColor),
    FIGHTING("Fighting", FightingTypeColor),
    FLYING("Flying", FlyingTypeColor),
    GROUND("Ground", GroundTypeColor),
    POISON("Poison", PoisonTypeColor),
    ROCK("Rock", RockTypeColor),
    BUG("Bug", BugTypeColor),
    GHOST("Ghost", GhostTypeColor),
    STEEL("Steel", SteelTypeColor),
    FIRE("Fire", FireTypeColor),
    WATER("Water", WaterTypeColor),
    GRASS("Grass", GrassTypeColor),
    ELECTRIC("Electric", ElectricTypeColor),
    PSYCHIC("Psychic", PsychicTypeColor),
    ICE("Ice", IceTypeColor),
    DRAGON("Dragon", DragonTypeColor),
    DARK("Dark", DarkTypeColor),
    FAIRY("Fairy", FairyTypeColor);

    companion object {
        fun fromTypeName(typeName: String?): PokemonTypes? {
            return PokemonTypes.entries.find { it.typeName == typeName }.orElse(NORMAL)
        }
    }
}