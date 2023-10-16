package com.loryblu.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LBRadioButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: RadioButtonColors
) {
    RadioButton(
        selected = isChecked,
        onClick = { onCheckedChange(isChecked) },
        colors = colors,
        modifier = Modifier.size(38.dp)
    )
}