package com.loryblu.core.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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