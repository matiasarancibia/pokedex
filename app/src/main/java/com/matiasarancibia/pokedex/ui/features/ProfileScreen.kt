package com.matiasarancibia.pokedex.ui.features

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
fun ProfileScreen() {
    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Profile Screen... Coming Soon!"
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    PokedexTheme {
        ProfileScreenContent()
    }
}