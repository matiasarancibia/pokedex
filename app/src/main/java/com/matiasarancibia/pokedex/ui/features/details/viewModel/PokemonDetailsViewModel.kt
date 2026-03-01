package com.matiasarancibia.pokedex.ui.features.details.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.matiasarancibia.pokedex.core.common.Result
import com.matiasarancibia.pokedex.core.common.UiState
import com.matiasarancibia.pokedex.core.network.NetworkErrorManager
import com.matiasarancibia.pokedex.domain.mappers.PokemonSpeciesSectionVDMapper
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.domain.useCase.GetPokemonDetailsUseCase
import com.matiasarancibia.pokedex.domain.useCase.ManagePokemonDetailsFromDBUseCase
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
    private val managePokemonDetailsFromDBUseCase: ManagePokemonDetailsFromDBUseCase,
    private val pokemonSpeciesSectionVDMapper: PokemonSpeciesSectionVDMapper,
    private val networkErrorManager: NetworkErrorManager
) : ViewModel() {

    private val _pokemonDetails = MutableStateFlow<UiState<PokemonDetailsViewData>>(UiState.default())
    val pokemonDetails: StateFlow<UiState<PokemonDetailsViewData>> = _pokemonDetails.asStateFlow()

    private val _isPokemonInFavorites = MutableStateFlow<Boolean>(false)
    val isPokemonInFavorites: StateFlow<Boolean> = _isPokemonInFavorites.asStateFlow()

    lateinit var pokemonDetailsData: PokemonDetailsViewData
        private set

    companion object {
        private const val ENGLISH_LANGUAGE = "en"
        private const val JAPAN_LANGUAGE = "ja"
        private const val NATIONAL_POKEDEX_ID = 1
    }

    fun playSound(
        url: String,
        onLoadingResource: (Boolean) -> Unit
    ) {
        exoPlayer.apply {
            addListener(
                object : Player.Listener {
                    override fun onIsLoadingChanged(isLoading: Boolean) {
                        super.onIsLoadingChanged(isLoading)
                        onLoadingResource(isLoading)
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)

                        if (playbackState == Player.STATE_ENDED) {
                            stop()
                            seekTo(0)
                        }
                    }
                }
            )

            // This is to stop any currently playing sound before starting a new one
            stop()
            clearMediaItems()

            // Here we set the sound url to be played
            setMediaItem(MediaItem.fromUri(url))

            // Then we prepare the player and then it will play the sound
            prepare()
            play()
        }
    }

    fun setPokemonDetailsData(data: PokemonDetailsViewData) {
        pokemonDetailsData = data
    }

    fun checkFavoriteState(
        pokemonNumber: Int?
    ) {
        viewModelScope.launch {
            when (val result = managePokemonDetailsFromDBUseCase.getFavoritesPokemonFromDB()) {
                is Result.Success -> {
                    val isPokemonFound = result.data.find { it.number == pokemonNumber } != null

                    _isPokemonInFavorites.value = isPokemonFound
                }

                is Result.Error -> {
                    _isPokemonInFavorites.value = false
                }
            }
        }
    }

    fun fetchPokemonDetails(
        pokemonNumber: Int
    ) {
        getStoredPokemonDetailsFromDB(
            pokemonNumber = pokemonNumber,
            onLocalPokemonDetailsNotFound = {
                viewModelScope.launch {
                    // We emit the loading state until we fetch all the data from the API
                    _pokemonDetails.value = UiState.loading()

                    val result = pokemonDetailsUseCase.fetchPokedexSpeciesInfo(NATIONAL_POKEDEX_ID, pokemonNumber)

                    when (result) {
                        is Result.Success -> {
                            val viewData = pokemonSpeciesSectionVDMapper.executeMapping(result.data)

                            viewData?.let { data ->
                                val entryText = data.flavorTextEntries.firstOrNull {
                                    it.language?.name == ENGLISH_LANGUAGE
                                }?.flavorText

                                // The 'cleanEntryText' extension will remove some special characters present in the original text
                                pokemonDetailsData.pokedexEntryText = entryText?.cleanEntryText()

                                /*
                                    We need to filter the pokemon names only for English and Japanese, and then we create a
                                    unique String value with both names separated by a dash
                                 */
                                pokemonDetailsData.pokemonNamesText = data.names.filter {
                                    it.language?.name == JAPAN_LANGUAGE || it.language?.name == ENGLISH_LANGUAGE
                                }.sortedBy {
                                    it.language?.name
                                }.joinToString(" - ") {
                                    it.name.orEmpty()
                                }

                                pokemonDetailsData.pokemonSpecies = data

                                /*
                                    We save the full details data from this pokemon in local DB to avoid extra API calls
                                    to re-fetch the information from the server
                                 */
                                managePokemonDetailsFromDBUseCase.savePokemonInfoToDB(
                                    pokemonDetailsData,
                                    data
                                )

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
        )
    }

    fun addToFavorites() {
        viewModelScope.launch {
            pokemonDetailsData.pokemonSpecies?.let { pokemonSpecies ->
                runCatching {
                    managePokemonDetailsFromDBUseCase.savePokemonAsFavoritesToDB(
                        pokemonDetailsData,
                        pokemonSpecies
                    )
                }.onSuccess {
                    _isPokemonInFavorites.value = true
                }.onFailure { e ->
                    _isPokemonInFavorites.value = false
                }
            }
        }
    }

    fun deleteFromFavorites(
        pokemonNumber: Int
    ) {
        viewModelScope.launch {
            pokemonDetailsData.pokemonSpecies?.let { pokemonSpecies ->
                runCatching {
                    managePokemonDetailsFromDBUseCase.deleteFavoritePokemonFromDB(
                        pokemonNumber = pokemonNumber
                    )
                }.onSuccess {
                    _isPokemonInFavorites.value = false
                }.onFailure { e ->
                    _isPokemonInFavorites.value = true
                }
            }
        }
    }

    private fun getStoredPokemonDetailsFromDB(
        pokemonNumber: Int,
        onLocalPokemonDetailsNotFound: () -> Unit
    ) {
        viewModelScope.launch {
            when (val response = managePokemonDetailsFromDBUseCase.getPokemonInfoFromDB(pokemonNumber)) {
                is Result.Success -> {
                    response.data?.let { viewData ->
                        pokemonDetailsData = viewData

                        _pokemonDetails.value = UiState.success(viewData)
                    } ?: run {
                        onLocalPokemonDetailsNotFound()
                    }
                }

                else -> {
                    onLocalPokemonDetailsNotFound()
                }
            }
        }
    }
}