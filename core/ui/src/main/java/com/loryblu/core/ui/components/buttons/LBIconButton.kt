package com.loryblu.core.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loryblu.core.ui.theme.LBSkyBlue

@Composable
fun LBIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        content = content
    )
}

@Preview
@Composable
private fun LBIconButtonPreview() {
    LBIconButton(
        onClick = {},
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun LBIconButtonPreview2() {
    LBIconButton(
        onClick = {},
        modifier = Modifier.background(LBSkyBlue, CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            tint = Color.White
        )
    }
}
