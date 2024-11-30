package com.loryblu.core.ui.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.theme.LBCardSoftBlue

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

@Preview(name = "Checked Button")
@Composable
private fun LBRadioButtonPreview() {
    LBRadioButton(
        isChecked = true,
        onCheckedChange = {},
        colors = RadioButtonColors(
            selectedColor = LBCardSoftBlue,
            unselectedColor = LBCardSoftBlue,
            disabledSelectedColor = LBCardSoftBlue,
            disabledUnselectedColor = LBCardSoftBlue
        )
    )
}

@Preview(name = "Unchecked Button")
@Composable
private fun LBRadioButtonPreview2() {
    LBRadioButton(
        isChecked = false,
        onCheckedChange = {},
        colors = RadioButtonColors(
            selectedColor = LBCardSoftBlue,
            unselectedColor = LBCardSoftBlue,
            disabledSelectedColor = LBCardSoftBlue,
            disabledUnselectedColor = LBCardSoftBlue
        )
    )
}

