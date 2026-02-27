package com.matiasarancibia.pokedex.ui.features.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme

@Composable
fun FavoritesScreen() {
    FavoritesScreenContent()
}

@Composable
private fun FavoritesScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Favorites Screen"
        )
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    PokedexTheme {
        FavoritesScreenContent()
    }
}