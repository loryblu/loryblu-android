package com.example.loryblu.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loryblu.ui.theme.Blue
import com.example.loryblu.ui.theme.White

@Composable
fun LBButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = { onClick() }, colors = ButtonDefaults.buttonColors(Blue),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = stringResource(textRes),
            color = White,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
    }
}

@Composable
@Preview
fun PreviewLBButton() {
    LBButton(
        textRes = 0,
        onClick = {},
        modifier = Modifier
    )
}