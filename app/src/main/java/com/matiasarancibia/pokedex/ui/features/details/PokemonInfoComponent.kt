package com.matiasarancibia.pokedex.ui.features.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.util.helpers.PokemonItemHelper

@Composable
fun PokemonInfoComponent(
    values: List<Triple<String, String, Int>>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        values.forEachIndexed { index, item ->
            val (title, value, iconResId) = item

            InfoItemComponent(
                title = title,
                value = value,
                iconRotationDegrees = if (title == stringResource(R.string.pokemon_height)) 90f else 0f,
                iconResId = iconResId,
                tint = Color.Gray
            )

            // Add a vertical divider between each item except the last one
            if (index < values.lastIndex) {
                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonInfoComponentPreview() {
    PokedexTheme {
        val data = PokemonItemHelper.createPokemonDetailsList()[0]

        PokemonInfoComponent(
            values = listOf(
                Triple(stringResource(R.string.pokemon_weight), stringResource(R.string.pokemon_weight_value, data.weight), R.drawable.ic_weight),
                Triple(stringResource(R.string.pokemon_height), stringResource(R.string.pokemon_height_value, data.height), R.drawable.ic_straighten),
                Triple(stringResource(R.string.pokemon_base_exp), data.baseExperience, R.drawable.ic_stat_exp)
            )
        )
    }
}