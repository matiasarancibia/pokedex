package com.matiasarancibia.pokedex.ui.features.details

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme

@Composable
fun InfoSectionTitleComponent(
    modifier: Modifier = Modifier,
    @DrawableRes titleResId: Int,
    titleColor: Color = MaterialTheme.colorScheme.onBackground
) {
    Text(
        modifier = modifier,
        text = stringResource(titleResId),
        style = MaterialTheme.typography.titleLarge.copy(
            color = titleColor,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun InfoSectionTitleComponentPreview() {
    PokedexTheme {
        InfoSectionTitleComponent(
            titleResId = R.string.pokemon_pokedex_entry_title
        )
    }
}