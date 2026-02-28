package com.matiasarancibia.pokedex.ui.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.core.common.UiState
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.useCase.ManagePokemonDetailsFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val managePokemonDetailsFromDBUseCase: ManagePokemonDetailsFromDBUseCase
) : ViewModel() {

    private val _favoritesState = MutableStateFlow<UiState<List<PokemonDetailsViewData>>>(UiState.default())
    val favoritesState: StateFlow<UiState<List<PokemonDetailsViewData>>> = _favoritesState.asStateFlow()

    fun getFavoritesFromDB() {
        viewModelScope.launch {
            _favoritesState.value = UiState.loading()

            when (val response = managePokemonDetailsFromDBUseCase.getFavoritesPokemonFromDB()) {
                is Result.Success -> {
                    if (response.data.isNotEmpty()) {
                        _favoritesState.value = UiState.success(response.data)
                    } else {
                        // If there's no pokemon added to favorites we emit an empty result
                        _favoritesState.value = UiState.emptyResult()
                    }
                }

                else -> {
                    // Do nothing
                }
            }
        }
    }
}