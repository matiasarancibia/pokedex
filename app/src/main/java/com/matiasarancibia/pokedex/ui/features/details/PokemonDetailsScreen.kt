package com.matiasarancibia.pokedex.ui.features.details

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.core.common.UiState
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.components.ImageItem
import com.matiasarancibia.pokedex.ui.components.LoadingComponent
import com.matiasarancibia.pokedex.ui.components.errorComponent.ErrorViewComponent
import com.matiasarancibia.pokedex.ui.features.details.viewModel.PokemonDetailsViewModel
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.shapes.DefaultRoundedCornerShape
import com.matiasarancibia.pokedex.ui.theme.shapes.LargeRoundedCornerShape
import com.matiasarancibia.pokedex.ui.util.extensions.letNotEmpty
import com.matiasarancibia.pokedex.ui.util.extensions.onClick
import com.matiasarancibia.pokedex.ui.util.extensions.orElse
import com.matiasarancibia.pokedex.ui.util.extensions.orFalse
import com.matiasarancibia.pokedex.ui.util.helpers.PokemonItemHelper

@Composable
fun PokemonDetailsScreen(
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    data: PokemonDetailsViewData,
    onBackPressed: () -> Unit,
    onCloseClick: () -> Unit
) {
    pokemonDetailsViewModel.setPokemonDetailsData(data)

    var isShinyImage by rememberSaveable { mutableStateOf(false) }
    var isSoundLoading by rememberSaveable { mutableStateOf(false) }
    val pokemonDetails by pokemonDetailsViewModel.pokemonDetails.collectAsStateWithLifecycle()

    when (val result = pokemonDetails) {
        is UiState.Loading -> {
            LoadingComponent(isFullScreen = true)
        }

        is UiState.Success -> {
            PokemonDetailsScreenContent(
                data = data,
                isSoundLoading = isSoundLoading,
                isShinyImage = isShinyImage,
                onBackPressed = onBackPressed,
                onImageClick = {
                    // This is to toggle between the normal and the shiny version of the pokemon
                    isShinyImage = !isShinyImage
                },
                onSoundButtonClick = { url ->
                    // Plays the cry sound of the pokemon
                    pokemonDetailsViewModel.playSound(
                        url = url,
                        onLoadingResource = { isLoading ->
                            isSoundLoading = isLoading
                        }
                    )
                }
            )
        }

        is UiState.Error -> {
            ErrorViewComponent(
                apiErrorVD = result.errorData,
                onCloseClick = {
                    onCloseClick()
                },
                onTryAgainClick = {
                    data.name?.letNotEmpty { pokemonName ->
                        // If there was an error we can try to get the pokemon details data again
                        pokemonDetailsViewModel.fetchPokemonDetails(pokemonName)
                    }
                }
            )
        }

        else -> {
            // Do nothing
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun PokemonDetailsScreenContent(
    data: PokemonDetailsViewData,
    isSoundLoading: Boolean,
    isShinyImage: Boolean,
    onSoundButtonClick: (String) -> Unit = {},
    onImageClick: () -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    val isInPreview = LocalInspectionMode.current
    val titlesColor = data.types.first().color
    val colorList = data.types.map { it.color }
    val typeColorsBrush = Brush.linearGradient(colors = if (colorList.size == 1) colorList + colorList else colorList)
    val generation = data.pokemonSpecies?.generation?.name
    val captureRate = data.pokemonSpecies?.captureRate.orElse(0)
    val baseHappiness = data.pokemonSpecies?.baseHappiness.orElse(0)
    val hasGenderDifferences = data.pokemonSpecies?.hasGenderDifferences.orFalse()
    val isBaby = data.pokemonSpecies?.isBaby.orFalse()
    val isLegendary = data.pokemonSpecies?.isLegendary.orFalse()
    val isMythical = data.pokemonSpecies?.isMythical.orFalse()

    val infiniteTransition = rememberInfiniteTransition(label = "loadingSound")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .onClick(
                                shape = CircleShape,
                                onClick = onBackPressed
                            )
                            .padding(5.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            )
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = typeColorsBrush
                    )
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Pokemon image
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ImageItem(
                        modifier = Modifier
                            .size(200.dp)
                            .onClick(
                                enabled = data.shinyImageUrl.isNotBlank(),
                                shape = DefaultRoundedCornerShape,
                                onClick = onImageClick
                            ),
                        isInPreview = isInPreview,
                        imageUrl = if (isShinyImage && data.shinyImageUrl.isNotBlank()) data.shinyImageUrl else data.officialArtworkImageUrl,
                        fakeImageRes = data.fakePokemonImageRes
                    )

                    IconButton(
                        onClick = {
                            data.cries?.latest.orElse(data.cries?.legacy)?.letNotEmpty { soundUrl ->
                                onSoundButtonClick(soundUrl)
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    color = Color.White.copy(alpha = 0.8f),
                                    shape = CircleShape
                                )
                                .padding(5.dp)
                                .graphicsLayer {
                                    rotationZ = if (isSoundLoading) rotation else 0f
                                },
                            imageVector = if (isSoundLoading) Icons.Default.Replay else Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }

                // Pokemon name and number
                BasicText(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(LargeRoundedCornerShape)
                        .background(Color.White.copy(alpha = 0.8f))
                        .padding(
                            vertical = 5.dp,
                            horizontal = 20.dp
                        ),
                    text = "#${data.formattedNumber} - ${data.formattedName}",
                    maxLines = 1,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 8.sp,
                        maxFontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                // Types
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clip(LargeRoundedCornerShape)
                        .background(Color.White.copy(alpha = 0.7f))
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    data.typeNamesList.forEachIndexed { index, type ->
                        Text(
                            modifier = Modifier
                                .background(
                                    color = colorList[index],
                                    shape = LargeRoundedCornerShape
                                )
                                .padding(
                                    vertical = 4.dp,
                                    horizontal = 10.dp
                                ),
                            text = type,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White
                            )
                        )
                    }
                }
            }

            // Info container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // About info
                InfoSectionTitleComponent(
                    modifier = Modifier.padding(top = 12.dp),
                    titleResId = R.string.pokemon_about_title,
                    titleColor = titlesColor
                )

                PokemonInfoComponent(
                    values = listOf(
                        Triple(
                            stringResource(R.string.pokemon_weight),
                            stringResource(R.string.pokemon_weight_value, data.weight),
                            R.drawable.ic_weight
                        ),
                        Triple(
                            stringResource(R.string.pokemon_height),
                            stringResource(R.string.pokemon_height_value, data.height),
                            R.drawable.ic_straighten
                        ),
                        Triple(stringResource(R.string.pokemon_base_exp), data.baseExperience, R.drawable.ic_stat_exp)
                    )
                )

                // Base Stats
                InfoSectionTitleComponent(
                    modifier = Modifier.padding(top = 12.dp),
                    titleResId = R.string.pokemon_stats_title,
                    titleColor = titlesColor
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    data.stats.forEach { statItem ->
                        StatProgressItem(
                            statItem = statItem,
                            brush = typeColorsBrush
                        )
                    }
                }

                // Pokedex entry
                data.pokedexEntryText?.letNotEmpty { pokedexEntryText ->
                    InfoSectionTitleComponent(
                        modifier = Modifier.padding(top = 12.dp),
                        titleResId = R.string.pokemon_pokedex_entry_title,
                        titleColor = titlesColor
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 1.dp,
                                    color = Color.LightGray
                                ),
                                shape = DefaultRoundedCornerShape
                            )
                            .padding(16.dp),
                        text = pokedexEntryText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily(Font(R.font.cascadia_italic)),
                            textAlign = TextAlign.Justify
                        )
                    )
                }

                // Characteristics
                InfoSectionTitleComponent(
                    modifier = Modifier.padding(vertical = 12.dp),
                    titleResId = R.string.pokemon_characteristics_title,
                    titleColor = titlesColor
                )

                data.pokemonNamesText?.letNotEmpty { pokemonNames ->
                    CharacteristicItemComponent(
                        data = Pair(R.string.characteristic_names_subtitle, pokemonNames),
                        titleColor = titlesColor
                    )
                }

                generation?.letNotEmpty { generationName ->
                    CharacteristicItemComponent(
                        data = Pair(R.string.characteristic_generation_subtitle, generationName),
                        titleColor = titlesColor
                    )
                }

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_capture_rate_subtitle, captureRate),
                    titleColor = titlesColor
                )

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_base_happiness_subtitle, baseHappiness),
                    titleColor = titlesColor
                )

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_has_gender_differences_subtitle, hasGenderDifferences),
                    titleColor = titlesColor
                )

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_is_baby_subtitle, isBaby),
                    titleColor = titlesColor
                )

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_is_legendary_subtitle, isLegendary),
                    titleColor = titlesColor
                )

                CharacteristicItemComponent(
                    data = Pair(R.string.characteristic_is_mythical_subtitle, isMythical),
                    titleColor = titlesColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonDetailsScreenPreview(
    @PreviewParameter(PokemonDetailsProvider::class) pokemonDetails: PokemonDetailsViewData
) {
    PokedexTheme {
        PokemonDetailsScreenContent(
            data = pokemonDetails,
            isSoundLoading = false,
            isShinyImage = false
        )
    }
}

private class PokemonDetailsProvider : PreviewParameterProvider<PokemonDetailsViewData> {
    override val values: Sequence<PokemonDetailsViewData> = PokemonItemHelper.createPokemonDetailsList().asSequence()
}