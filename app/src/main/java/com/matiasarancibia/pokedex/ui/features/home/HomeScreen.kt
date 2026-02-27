package com.matiasarancibia.pokedex.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.components.LoadingComponent
import com.matiasarancibia.pokedex.ui.theme.MediumRoundedCornerShape
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.util.helpers.PokemonItemHelper
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<PokemonDetailsViewData>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit = {}
) {
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(key1 = isRefreshing) {
        if (isRefreshing) {
            pullToRefreshState.snapTo(0f)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.White, shape = RoundedCornerShape(50.dp))
                                .padding(2.dp),
                            painter = painterResource(R.drawable.ic_pokeball),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                        Text(
                            text = stringResource(R.string.app_name),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                )
            )
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
                                onImageClick = {

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

        HomeScreen(
            data = pokemonFakeStream,
            isRefreshing = isRefreshing
        )
    }
}

private class HomeScreenProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(false, true)
}