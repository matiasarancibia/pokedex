package com.matiasarancibia.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.matiasarancibia.pokedex.ui.components.errorComponent.ErrorViewComponent
import com.matiasarancibia.pokedex.ui.features.details.viewModel.PokemonDetailsViewModel
import com.matiasarancibia.pokedex.ui.features.home.HomeScreen
import com.matiasarancibia.pokedex.ui.features.home.viewModel.HomeViewModel
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val homeViewModel: HomeViewModel by viewModels()
    val pokemonDetailsViewModel: PokemonDetailsViewModel by viewModels()
    private var isLoading by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isLoading
            }
        }

        lifecycleScope.launch {
            delay(1000)
            isLoading = false

            enableEdgeToEdge()

            setContent {
                PokedexTheme {
                    val pokemonState = homeViewModel.pokemonStream.collectAsLazyPagingItems()
                    val isRefreshing = homeViewModel.isRefreshing.collectAsStateWithLifecycle().value

                    when (val state = pokemonState.loadState.refresh) {
                        is LoadState.Loading -> {
                            homeViewModel.setRefreshing(true)

                            HomeScreen(
                                data = pokemonState,
                                isRefreshing = isRefreshing
                            )
                        }

                        is LoadState.NotLoading -> {
                            homeViewModel.setRefreshing(false)

                            HomeScreen(
                                data = pokemonState,
                                isRefreshing = isRefreshing,
                                onRefresh = {
                                    pokemonState.refresh()
                                }
                            )
                        }

                        is LoadState.Error -> {
                            ErrorViewComponent(
                                apiErrorVD = homeViewModel.getApiErrorViewData(state.error),
                                onCloseClick = {
                                    this@MainActivity.finish()
                                },
                                onTryAgainClick = {
                                    pokemonState.retry()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}