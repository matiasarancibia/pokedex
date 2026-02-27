package com.matiasarancibia.pokedex.domain.useCase

import com.matiasarancibia.pokedex.data.repository.PokemonListRepositoryImpl
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonListRepository: PokemonListRepositoryImpl
) {
    fun execute(limit: Int?) = pokemonListRepository.getPokemonStream(pageSize = limit)
}