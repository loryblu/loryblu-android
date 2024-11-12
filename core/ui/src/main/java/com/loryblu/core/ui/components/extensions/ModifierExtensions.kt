package com.loryblu.core.ui.components.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.loryShadow(
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    shadowColor: Color = Color.Black.copy(alpha = 0.2f),
    cornerRadius: Dp = 0.dp,
    alpha: Float = 0.7f,
): Modifier {
    return this.drawBehind {
        drawRoundRect(
            color = shadowColor,
            topLeft = Offset(x = offsetX.toPx(), y = offsetY.toPx()),
            size = size,
            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
            alpha = alpha,
        )
    }
}
