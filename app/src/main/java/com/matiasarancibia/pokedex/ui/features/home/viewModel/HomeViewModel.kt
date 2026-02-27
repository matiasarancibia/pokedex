package com.matiasarancibia.pokedex.ui.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.matiasarancibia.pokedex.core.network.NetworkErrorManager
import com.matiasarancibia.pokedex.domain.useCase.GetPokemonListUseCase
import com.matiasarancibia.pokedex.ui.util.extensions.empty
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonListUseCase: GetPokemonListUseCase,
    private val networkErrorManager: NetworkErrorManager
) : ViewModel() {

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _searchQuery = MutableStateFlow(String.empty())
    val searchQuery = _searchQuery.asStateFlow()

    private val limit = 15

    val pokemonStream = pokemonListUseCase.execute(limit).cachedIn(viewModelScope)

    // We combine both flows to access the 'pokemonStream' data and filter it by a specific name
    val filteredPokemonList = combine(
        pokemonStream,
        _searchQuery
    ) { pagingData, query ->
        if (query.isBlank()) {
            pagingData
        } else {
            // Returns only the values that matches with the given query text value
            pagingData.filter { pokemon ->
                // The filter accepts names and also numbers to filter
                pokemon.name?.contains(query, ignoreCase = true).orElse(false) || pokemon.number?.toString()?.contains(query).orElse(false)
            }
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun getApiErrorViewData(
        exception: Throwable?,
        showCloseButton: Boolean = false,
        showSettingButton: Boolean = false,
        showTryAgainButton: Boolean = false,
        showCustomActionButton: Boolean = false,
    ) = networkErrorManager.handleAPIErrorResponse(exception).apply {
        showClose = showCloseButton
        showSetting = showSettingButton
        showTryAgain = showTryAgainButton
        showCustomAction = showCustomActionButton

    }

    fun setRefreshing(isRefreshing: Boolean) {
        _isRefreshing.value = isRefreshing
    }
}