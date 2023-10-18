package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LBButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    buttonColors: ButtonColors,
    textColor: Color,
    areAllFieldsValid: Boolean
) {
    Button(
        enabled = areAllFieldsValid,
        onClick = { onClick() },
        colors = buttonColors,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = stringResource(textRes),
            fontWeight = FontWeight.Bold,
            color = textColor,
            fontSize = 20.sp,
        )
    }
}