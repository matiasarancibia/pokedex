package com.matiasarancibia.pokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.features.ProfileScreen
import com.matiasarancibia.pokedex.ui.features.favorites.FavoritesScreen
import com.matiasarancibia.pokedex.ui.features.favorites.FavoritesViewModel
import com.matiasarancibia.pokedex.ui.features.home.HomeScreen
import com.matiasarancibia.pokedex.ui.features.home.viewModel.HomeViewModel
import com.matiasarancibia.pokedex.ui.navigation.BottomNavigationItems
import com.matiasarancibia.pokedex.ui.navigation.NotchedBottomBarWithFab
import com.matiasarancibia.pokedex.ui.navigation.Screens
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val homeViewModel: HomeViewModel by viewModels()
    val favoritesViewModel: FavoritesViewModel by viewModels()

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
                    val context = LocalContext.current
                    val navController = rememberNavController()
                    val startDestination = BottomNavigationItems.HOME
                    var selectedScreen by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            NotchedBottomBarWithFab(
                                modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()),
                                navController = navController,
                                startDestination = startDestination,
                                selectedScreen = selectedScreen
                            )
                        }
                    ) { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = Screens.HomeScreen,
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -it }
                                )
                            },
                            popEnterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { -it }
                                )
                            },
                            popExitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { it }
                                )
                            }
                        ) {
                            BottomNavigationItems.entries.forEachIndexed { index, bottomNavigationItem ->
                                composable(bottomNavigationItem.screen::class) {
                                    selectedScreen = index

                                    when (bottomNavigationItem.screen) {
                                        Screens.HomeScreen -> {
                                            HomeScreen(
                                                /*
                                                    We pass the view model so in this way there is no a new call to the API every time
                                                    we navigate to the Home screen
                                                 */
                                                homeViewModel = homeViewModel,
                                                onItemClick = { pokemonDetails ->
                                                    navigateToDetailsScreen(
                                                        context,
                                                        pokemonDetails,
                                                        onIntentReady = { intent ->
                                                            startActivity(intent)
                                                        }
                                                    )
                                                },
                                                onCloseClick = {
                                                    this@MainActivity.finish()
                                                }
                                            )
                                        }

                                        Screens.FavoritesScreen -> {
                                            val launcher = rememberLauncherForActivityResult(
                                                contract = ActivityResultContracts.StartActivityForResult()
                                            ) {
                                                favoritesViewModel.getFavoritesFromDB()
                                            }

                                            FavoritesScreen(
                                                favoritesViewModel = favoritesViewModel,
                                                onItemClick = { pokemonDetails ->
                                                    navigateToDetailsScreen(
                                                        context,
                                                        pokemonDetails,
                                                        onIntentReady = { intent ->
                                                            launcher.launch(intent)
//                                                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                                        }
                                                    )
                                                },
                                                onBackPressed = {
                                                    navController.popBackStack()
                                                }
                                            )
                                        }

                                        Screens.ProfileScreen -> {
                                            ProfileScreen()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetailsScreen(
        context: Context,
        data: PokemonDetailsViewData,
        onIntentReady: (Intent) -> Unit
    ) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.POKEMON_DETAILS_DATA, data)
        onIntentReady(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out)
    }
}