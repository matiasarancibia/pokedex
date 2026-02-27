package com.matiasarancibia.pokedex.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.components.LoadingComponent
import com.matiasarancibia.pokedex.ui.components.errorComponent.ErrorViewComponent
import com.matiasarancibia.pokedex.ui.features.home.viewModel.HomeViewModel
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.theme.shapes.MediumRoundedCornerShape
import com.matiasarancibia.pokedex.ui.util.extensions.empty
import com.matiasarancibia.pokedex.ui.util.helpers.PokemonItemHelper
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onItemClick: (PokemonDetailsViewData) -> Unit,
    onCloseClick: () -> Unit
) {
    val pokemonState = homeViewModel.filteredPokemonList.collectAsLazyPagingItems()
    val isRefreshing = homeViewModel.isRefreshing.collectAsStateWithLifecycle().value
    val searchQuery by homeViewModel.searchQuery.collectAsState()

    when (val state = pokemonState.loadState.refresh) {
        is LoadState.Loading -> {
            homeViewModel.setRefreshing(true)

            HomeScreenContent(
                data = pokemonState,
                isRefreshing = isRefreshing
            )
        }

        is LoadState.NotLoading -> {
            homeViewModel.setRefreshing(false)

            HomeScreenContent(
                data = pokemonState,
                isSearchEnabled = true,
                searchQuery = searchQuery,
                isRefreshing = isRefreshing,
                onSearch = {
                    homeViewModel.onSearchQueryChanged(it)
                },
                onItemClick = {
                    onItemClick(it)
                },
                onRefresh = {
                    homeViewModel.onSearchQueryChanged(String.empty())
                    pokemonState.refresh()
                }
            )
        }

        is LoadState.Error -> {
            ErrorViewComponent(
                apiErrorVD = homeViewModel.getApiErrorViewData(state.error),
                onCloseClick = {
                    onCloseClick()
                },
                onTryAgainClick = {
                    pokemonState.retry()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    data: LazyPagingItems<PokemonDetailsViewData>,
    isSearchEnabled: Boolean = false,
    searchQuery: String = String.empty(),
    isRefreshing: Boolean,
    onItemClick: (PokemonDetailsViewData) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(key1 = isRefreshing) {
        if (isRefreshing) {
            pullToRefreshState.snapTo(0f)
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CenterAlignedTopAppBar(
                    windowInsets = WindowInsets(top = 10.dp),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.White, shape = CircleShape)
                                    .padding(1.dp),
                                painter = painterResource(R.drawable.ic_pokeball_flat),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )

                            Text(
                                text = stringResource(R.string.app_name),
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 26.sp
                                )
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.96f)
                        .clip(CircleShape),
                    shape = CircleShape,
                    value = searchQuery,
                    enabled = isSearchEnabled,
                    onValueChange = {
                        onSearch(it)
                    },
                    placeholder = { Text("Search Pokémon") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(
                                onClick = {
                                    onSearch(String.empty())
                                }
                            ) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(innerPadding)
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                state = pullToRefreshState,
                indicator = {
                    Indicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = isRefreshing,
                        state = pullToRefreshState,
                        color = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                }
            ) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth(0.96f)
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MediumRoundedCornerShape
                        ),
                    columns = GridCells.Adaptive(100.dp),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(data.itemCount) { index ->
                        val item = data[index]

                        item?.let {
                            PokemonItemComponent(
                                modifier = Modifier.padding(5.dp),
                                data = it,
                                onItemClick = {
                                    onItemClick(it)
                                }
                            )
                        }
                    }
                }
            }

            if (isRefreshing) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.96f)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MediumRoundedCornerShape
                        )
                ) {
                    LoadingComponent()
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenProvider::class) isRefreshing: Boolean
) {
    PokedexTheme(
        darkTheme = true
    ) {
        val pokemonDetailsList = PokemonItemHelper.createPokemonDetailsList()
        val pokemonFakeStream = flowOf(PagingData.from(pokemonDetailsList)).collectAsLazyPagingItems()

        HomeScreenContent(
            data = pokemonFakeStream,
            isRefreshing = isRefreshing
        )
    }
}

private class HomeScreenProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}