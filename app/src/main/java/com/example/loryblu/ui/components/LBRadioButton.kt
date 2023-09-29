package com.example.loryblu.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LBRadioButton(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
) {
    RadioButton(
        selected = isChecked,
        onClick = { onCheckedChange(isChecked) },
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = Color.Gray
        )
    )

}

@Composable
@Preview
fun PreviewLBRadioButton() {
    LBRadioButton(
        isChecked = true,
        onCheckedChange = {},
        modifier = Modifier
    )
}