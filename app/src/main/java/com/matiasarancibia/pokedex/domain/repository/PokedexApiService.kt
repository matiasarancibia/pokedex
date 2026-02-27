package com.matiasarancibia.pokedex.domain.repository

import com.matiasarancibia.pokedex.data.model.PokedexSectionData
import com.matiasarancibia.pokedex.data.model.PokemonDetailsData
import com.matiasarancibia.pokedex.data.model.PokemonListResponseData
import com.matiasarancibia.pokedex.data.model.PokemonSpeciesSectionData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/*
    This is the main API service interface to declare the needed API call functions
    to get the data from the server
 */
interface PokedexApiService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponseData

    @GET("pokedex/{pokedexNumber}")
    suspend fun getPokedexEntry(
        @Path("pokedexNumber") pokedexNumber: Int
    ): PokedexSectionData

    @GET
    suspend fun getPokemonSpecies(
        @Url pokemonSpeciesUrl: String
    ): PokemonSpeciesSectionData

    @GET
    suspend fun getPokemonDetailsByUrl(
        @Url pokemonUrl: String
    ): PokemonDetailsData
}