package com.matiasarancibia.pokedex.ui.theme.shapes

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.matiasarancibia.pokedex.ui.util.toPx

class FabNotchShape(
    private val fabRadiusDp: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return GenericShape { size, _ ->
            val width = size.width
            val height = size.height
            val fabRadius = fabRadiusDp.toPx()
            val fabDiameter = fabRadius * 2
            val centerX = width / 2f
            val notchWidth = fabDiameter + 32f
            val notchHeight = fabRadius + 20f

            moveTo(0f, 0f)
            lineTo(centerX - notchWidth / 2f, 0f)

            // Left curve into the notch
            cubicTo(
                centerX - fabRadius - 20f, 15f,
                centerX - fabRadius, notchHeight,
                centerX, notchHeight
            )

            // Right curve out of the notch
            cubicTo(
                centerX + fabRadius, notchHeight,
                centerX + fabRadius + 20f, 15f,
                centerX + notchWidth / 2f, 0f
            )

            lineTo(width, 0f)
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }.createOutline(
            size = size,
            layoutDirection = layoutDirection,
            density = density
        )
    }
}