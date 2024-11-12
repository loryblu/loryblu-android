package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun LBTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors,
    @StringRes textRes: Int
) {
    TextButton(
        onClick = onClick,
        colors = buttonColors,
        modifier = modifier
    ) {
        Text(text = stringResource(id = textRes))
    }
}