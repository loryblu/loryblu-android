package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LBTransparentButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes textRes: Int
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = Color.Transparent
        ),
        modifier = modifier.size(96.dp, 35.dp)
    ) {
        Text(text = stringResource(id = textRes), fontSize = 17.sp)
    }
}
