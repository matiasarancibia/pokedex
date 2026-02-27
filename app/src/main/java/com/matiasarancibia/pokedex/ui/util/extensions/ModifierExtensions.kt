package com.matiasarancibia.pokedex.ui.util.extensions

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape

inline fun Modifier.ifCondition(
    condition: Boolean,
    block: Modifier.() -> Modifier
): Modifier = if (condition) this.block() else this

fun Modifier.onClick(
    enabled: Boolean = true,
    shape: Shape? = null,
    onClick: () -> Unit,
): Modifier = this
    .ifCondition(shape != null) { this.clip(shape!!) }
    .clickable(enabled = enabled) { onClick() }