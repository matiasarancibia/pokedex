package com.matiasarancibia.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.matiasarancibia.pokedex.domain.model.APIErrorViewData
import com.matiasarancibia.pokedex.domain.model.PokemonDetailsViewData
import com.matiasarancibia.pokedex.ui.components.errorComponent.ErrorViewComponent
import com.matiasarancibia.pokedex.ui.features.details.PokemonDetailsScreen
import com.matiasarancibia.pokedex.ui.features.details.viewModel.PokemonDetailsViewModel
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    private val pokemonDetailsViewModel: PokemonDetailsViewModel by viewModels()

    companion object {
        const val POKEMON_DETAILS_DATA = "PokemonDetailsData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val pokemonDetailsViewData = intent.getSerializableExtra(POKEMON_DETAILS_DATA) as? PokemonDetailsViewData

        setContent {
            PokedexTheme {
                if (pokemonDetailsViewData != null && pokemonDetailsViewData.number != null && !pokemonDetailsViewData.name.isNullOrBlank()) {
                    LaunchedEffect(Unit) {
                        pokemonDetailsViewModel.setPokemonDetailsData(pokemonDetailsViewData)

                        pokemonDetailsViewModel.fetchPokemonDetails(
                            pokemonDetailsViewData.number,
                            pokemonDetailsViewData.name
                        )
                    }

                    PokemonDetailsScreen(
                        pokemonDetailsViewModel = pokemonDetailsViewModel,
                        data = pokemonDetailsViewData,
                        onBackPressed = {
                            this@DetailsActivity.finish()
                        },
                        onCloseClick = {
                            this@DetailsActivity.finish()
                        }
                    )
                } else {
                    ErrorViewComponent(
                        APIErrorViewData()
                    )
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_right)
    }
}