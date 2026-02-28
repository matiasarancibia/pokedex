package com.matiasarancibia.pokedex.ui.features.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.core.common.UiState
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.components.LoadingComponent
import com.matiasarancibia.pokedex.ui.features.home.PokemonItemComponent
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.shapes.MediumRoundedCornerShape
import com.matiasarancibia.pokedex.ui.util.extensions.onClick

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel,
    onItemClick: (PokemonDetailsViewData) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    val favoritesState by favoritesViewModel.favoritesState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        favoritesViewModel.getFavoritesFromDB()
    }

    when (val state = favoritesState) {
        is UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            FavoritesScreenContent(
                data = state.data,
                onItemClick = onItemClick,
                onBackPressed = onBackPressed
            )
        }

        is UiState.EmptyResult -> {
            EmptyResultsComponent()
        }

        else -> {
            // Do nothing
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoritesScreenContent(
    data: List<PokemonDetailsViewData>,
    onItemClick: (PokemonDetailsViewData) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    windowInsets = WindowInsets(top = 0.dp),
                    title = {
                        Text(
                            text = stringResource(R.string.bottom_navigation_favorites),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Default,
                                fontSize = 18.sp
                            )
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
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

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MediumRoundedCornerShape
                )
                .padding(innerPadding),
            columns = GridCells.Adaptive(100.dp),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(data.size) { index ->
                val item = data[index]

                PokemonItemComponent(
                    modifier = Modifier.padding(5.dp),
                    data = item,
                    onItemClick = {
                        onItemClick(it)
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EmptyResultsComponent(
    onBackPressed: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    windowInsets = WindowInsets(top = 0.dp),
                    title = {
                        Text(
                            text = stringResource(R.string.bottom_navigation_favorites),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Default,
                                fontSize = 18.sp
                            )
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
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

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(32.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier.padding(top = 60.dp),
                text = stringResource(R.string.error_results_not_found),
                style = MaterialTheme.typography.titleLarge.copy(
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    PokedexTheme {
//        FavoritesScreenContent(
//            data = PokemonItemHelper.createPokemonDetailsList()
//        )

        EmptyResultsComponent()
    }
}