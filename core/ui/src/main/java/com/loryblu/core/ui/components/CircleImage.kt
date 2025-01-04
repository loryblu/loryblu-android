package com.loryblu.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.R

@Composable
fun CircleImage(
    @DrawableRes image: Int,
    backgroundColor: Color,
    size: Int = 96,
    tint: Color? = null
) {
    Image(
        painter = painterResource(image),
        colorFilter = tint?.let {
            ColorFilter.tint(it)
        },
        contentDescription = null,
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    )
}

@Preview
@Composable
private fun CircleImagePreview() {
    CircleImage(R.drawable.ic_launcher_foreground, White)
}
