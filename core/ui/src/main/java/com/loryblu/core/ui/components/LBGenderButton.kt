package com.loryblu.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LBGenderButton(
    onClick: () -> Unit,
    modifier: Modifier,
    borderStrokeAndColors: BorderStroke,
    buttonColors: ButtonColors,
    painterRes: Painter,
    contentDescriptionRes: String,
    iconColor: Color,
    textRes: String,
    textColor: Color,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(end = 16.dp, start = 8.dp),
        colors = buttonColors,
        border = borderStrokeAndColors,
    ) {
        Icon(
            painter = painterRes,
            contentDescription = contentDescriptionRes,
            tint = iconColor,
            modifier = Modifier.padding(end = 12.dp, start = 6.dp),
        )
        Text(
            text = textRes,
            color = textColor,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}