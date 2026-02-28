package com.matiasarancibia.pokedex.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsEntity
import com.matiasarancibia.pokedex.core.database.entities.PokemonDetailsFullEntity
import com.matiasarancibia.pokedex.domain.model.CriesViewData
import com.matiasarancibia.pokedex.domain.model.EvolutionChainViewData
import com.matiasarancibia.pokedex.domain.model.FlavorTextEntryItemViewData
import com.matiasarancibia.pokedex.domain.model.GenerationViewData
import com.matiasarancibia.pokedex.domain.model.GrowthRateViewData
import com.matiasarancibia.pokedex.domain.model.LanguageViewData
import com.matiasarancibia.pokedex.domain.model.NameItemViewData
import com.matiasarancibia.pokedex.domain.model.StatsItemViewData
import com.matiasarancibia.pokedex.domain.model.StatsViewData
import com.matiasarancibia.pokedex.ui.util.PokemonTypes

class PokemonTypesConverters {

    private val gson = Gson()

    @TypeConverter
    fun pokemonTypeToString(value: PokemonTypes): String? = value.typeName

    @TypeConverter
    fun stringToPokemonType(value: String?): PokemonTypes? = value?.let { PokemonTypes.fromTypeName(it) }

    @TypeConverter
    fun pokemonTypesListToString(list: List<PokemonTypes>?): String? {
        return list?.joinToString(separator = ",") { it.typeName }
    }

    @TypeConverter
    fun stringToPokemonTypesList(value: String?): List<PokemonTypes>? {
        if (value.isNullOrBlank()) return emptyList()

        return value
            .split(",")
            .mapNotNull { PokemonTypes.fromTypeName(it) }
    }

    @TypeConverter
    fun stringListToString(list: List<String>?): String? {
        return list?.joinToString(separator = ",") { it }
    }

    @TypeConverter
    fun stringToStringList(value: String?): List<String>? {
        if (value.isNullOrBlank()) return emptyList()

        return value
            .split(",")
            .map { it }
    }

    @TypeConverter
    fun nullableStringListToString(list: List<String?>?): String? {
        return list?.joinToString(separator = ",") { it.orEmpty() }
    }

    @TypeConverter
    fun stringToNullableStringList(value: String?): List<String?>? {
        if (value.isNullOrBlank()) return emptyList()

        return value
            .split(",")
            .map { it }
    }

    // region Entities converters

    @TypeConverter
    fun fromPokemonDetailsEntityList(list: List<PokemonDetailsEntity>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPokemonDetailsEntityList(value: String?): List<PokemonDetailsEntity>? {
        return value?.let { gson.fromJson(it, object : TypeToken<List<PokemonDetailsEntity>>() {}.type) }
    }

    @TypeConverter
    fun fromPokemonDetailsEntity(value: PokemonDetailsEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPokemonDetailsEntity(value: String?): PokemonDetailsEntity? {
        return value?.let { gson.fromJson(it, PokemonDetailsEntity::class.java) }
    }

    @TypeConverter
    fun fromPokemonDetailsFullEntity(value: PokemonDetailsFullEntity?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toPokemonDetailsFullEntity(value: String?): PokemonDetailsFullEntity? {
        return value?.let { gson.fromJson(it, PokemonDetailsFullEntity::class.java) }
    }
    // endregion

    // region ViewData converters
    @TypeConverter
    fun fromGeneration(value: GenerationViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toGeneration(value: String?): GenerationViewData? {
        return value?.let { gson.fromJson(it, GenerationViewData::class.java) }
    }

    @TypeConverter
    fun fromEvolutionChainViewData(value: EvolutionChainViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toEvolutionChainViewData(value: String?): EvolutionChainViewData? {
        return value?.let { gson.fromJson(it, EvolutionChainViewData::class.java) }
    }

    @TypeConverter
    fun fromGrowthRateViewData(value: GrowthRateViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toGrowthRateViewData(value: String?): GrowthRateViewData? {
        return value?.let { gson.fromJson(it, GrowthRateViewData::class.java) }
    }

    @TypeConverter
    fun fromLanguageViewData(value: LanguageViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toLanguageViewData(value: String?): LanguageViewData? {
        return value?.let { gson.fromJson(it, LanguageViewData::class.java) }
    }

    @TypeConverter
    fun fromCriesViewData(value: CriesViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCriesViewData(value: String?): CriesViewData? {
        return value?.let { gson.fromJson(it, CriesViewData::class.java) }
    }

    @TypeConverter
    fun fromStatsViewData(value: StatsViewData?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStatsViewData(value: String?): StatsViewData? {
        return value?.let { gson.fromJson(it, StatsViewData::class.java) }
    }

    // Lists
    @TypeConverter
    fun fromFlavorTextEntryItemViewDataList(list: List<FlavorTextEntryItemViewData>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toFlavorTextEntryItemViewDataList(value: String?): List<FlavorTextEntryItemViewData>? {
        return value?.let { gson.fromJson(it, object : TypeToken<List<GrowthRateViewData>>() {}.type) }
    }

    @TypeConverter
    fun fromNameItemViewDataList(list: List<NameItemViewData>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toNameItemViewData(value: String?): List<NameItemViewData>? {
        return value?.let { gson.fromJson(it, object : TypeToken<List<NameItemViewData>>() {}.type) }
    }

    @TypeConverter
    fun fromStatsItemViewDataList(list: List<StatsItemViewData>?): String? {
        return list?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStatsItemViewData(value: String?): List<StatsItemViewData>? {
        return value?.let { gson.fromJson(it, object : TypeToken<List<StatsItemViewData>>() {}.type) }
    }
    // endregion
}
