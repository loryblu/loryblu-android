package com.loryblu.core.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R

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

@Preview
@Composable
private fun LBButtonPreview() {
    LBButton(
        textRes = R.string.select_the_date,
        onClick = {},
        buttonColors = ButtonDefaults.buttonColors(),
        textColor = Color.White,
        areAllFieldsValid = true
    )
}
