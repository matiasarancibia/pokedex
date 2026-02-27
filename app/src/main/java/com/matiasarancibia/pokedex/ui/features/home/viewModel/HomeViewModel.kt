package com.matiasarancibia.pokedex.ui.features.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.matiasarancibia.pokedex.core.network.NetworkErrorManager
import com.matiasarancibia.pokedex.domain.useCase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonListUseCase: GetPokemonListUseCase,
    private val networkErrorManager: NetworkErrorManager
) : ViewModel() {

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val limit = 15

    val pokemonStream = pokemonListUseCase.execute(limit).cachedIn(viewModelScope)

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
        viewModelScope.launch {
            _isRefreshing.value = isRefreshing
        }
    }
}