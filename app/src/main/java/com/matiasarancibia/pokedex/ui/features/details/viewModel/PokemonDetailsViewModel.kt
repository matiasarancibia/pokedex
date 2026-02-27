package com.matiasarancibia.pokedex.ui.features.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.core.common.UiState
import com.matiasarancibia.pokedex.core.network.NetworkErrorManager
import com.matiasarancibia.pokedex.domain.mappers.PokemonSpeciesSectionVDMapper
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.useCase.GetPokemonDetailsUseCase
import com.matiasarancibia.pokedex.ui.util.extensions.cleanEntryText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val exoPlayer: ExoPlayer,
    private val pokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val pokemonSpeciesSectionVDMapper: PokemonSpeciesSectionVDMapper,
    private val networkErrorManager: NetworkErrorManager
) : ViewModel() {

    private val _pokemonDetails = MutableStateFlow<UiState<PokemonDetailsViewData>>(UiState.default())
    val pokemonDetails: StateFlow<UiState<PokemonDetailsViewData>> = _pokemonDetails.asStateFlow()

    lateinit var pokemonDetailsData: PokemonDetailsViewData
        private set

    companion object {
        private const val ENGLISH_LANGUAGE = "en"
        private const val JAPAN_LANGUAGE = "ja"
    }

    fun playSound(url: String) {
        exoPlayer.apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            play()
        }
    }

    fun setPokemonDetailsData(data: PokemonDetailsViewData) {
        pokemonDetailsData = data
    }

    fun fetchPokemonDetails(
        pokemonName: String
    ) {
        viewModelScope.launch {
            // We emit the loading state until we fetch all the data from the API
            _pokemonDetails.value = UiState.loading()

            val result = pokemonDetailsUseCase.getPokedexEntry(1, pokemonName)

            when (result) {
                is Result.Success -> {
                    val viewData = pokemonSpeciesSectionVDMapper.executeMapping(result.data)

                    viewData?.let { data ->
                        val entryText = data.flavorTextEntries?.firstOrNull {
                            it.language?.name == ENGLISH_LANGUAGE
                        }?.flavorText

                        // The 'cleanEntryText' extension will remove some special characters present in the original text
                        pokemonDetailsData.pokedexEntryText = entryText?.cleanEntryText()

                        /*
                            We need to filter the pokemon names only for English and Japanese, and then we create a
                            unique String value with both names separated by a dash
                         */
                        pokemonDetailsData.pokemonNamesText = data.names?.filter {
                            it.language?.name == JAPAN_LANGUAGE || it.language?.name == ENGLISH_LANGUAGE
                        }?.sortedBy {
                            it.language?.name
                        }?.joinToString(" - ") {
                            it.name.orEmpty()
                        }

                        pokemonDetailsData.pokemonSpecies = data

                        _pokemonDetails.value = UiState.success(pokemonDetailsData)
                    } ?: run {
                        _pokemonDetails.value = UiState.emptyResult()
                    }
                }

                is Result.Error -> {
                    val apiErrorVD = networkErrorManager.handleAPIErrorResponse(result.exception)

                    apiErrorVD.apply {
                        showClose = true
                        showTryAgain = true
                    }

                    _pokemonDetails.value = UiState.error(apiErrorVD)
                }
            }
        }
    }

    override fun onCleared() {
        exoPlayer.release()
    }
}