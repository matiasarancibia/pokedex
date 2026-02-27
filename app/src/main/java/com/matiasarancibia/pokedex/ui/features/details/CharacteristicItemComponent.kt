package com.matiasarancibia.pokedex.ui.features.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matiasarancibia.pokedex.R
import com.matiasarancibia.pokedex.ui.theme.PokedexTheme
import com.matiasarancibia.pokedex.ui.util.extensions.letNotEmpty

@Composable
fun CharacteristicItemComponent(
    data: Pair<Int, Any?>,
    titleColor: Color
) {
    val (titleResId, value) = data

    value?.let { item ->
        val itemValue = if (item is Boolean) {
            if (item) {
                stringResource(R.string.characteristic_yes_value)
            } else {
                stringResource(R.string.characteristic_no_value)
            }
        } else {
            item.toString()
        }

        itemValue.letNotEmpty {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                // Characteristic title
                Text(
                    text = stringResource(titleResId),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = titleColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                // Characteristic value
                Text(
                    text = itemValue,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacteristicItemComponentPreview() {
    PokedexTheme {
        CharacteristicItemComponent(
            data = Pair(R.string.pokemon_weight, "19 kg"),
            titleColor = MaterialTheme.colorScheme.primary
        )
    }
}