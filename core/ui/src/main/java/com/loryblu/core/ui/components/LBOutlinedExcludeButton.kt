package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun LBOutlinedExcludeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors,
    @StringRes textRes: Int
) {
    OutlinedButton(
        onClick = onClick,
        colors = buttonColors,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.error
        ),
        modifier = modifier
    ) {
        Text(text = stringResource(id = textRes))
    }
}