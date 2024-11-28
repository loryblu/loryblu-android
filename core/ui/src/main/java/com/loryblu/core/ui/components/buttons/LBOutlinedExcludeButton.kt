package com.loryblu.core.ui.components.buttons

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.R

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

@Preview
@Composable
private fun LBOutlinedExcludeButtonPreview() {
    LBOutlinedExcludeButton(
        onClick = {},
        buttonColors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.error
        ),
        textRes = R.string.login_title
    )
}
