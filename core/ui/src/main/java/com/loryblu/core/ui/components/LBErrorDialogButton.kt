package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBErrorColor

@Composable
fun LBNegativeDialogButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = LBErrorColor,
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = LBErrorColor
        ),
        modifier = modifier.size(96.dp, 35.dp)
    ) {
        Text(text = stringResource(id = textRes), fontSize = 17.sp)
    }
}

@Preview
@Composable
fun LBNegativeDialogButtonPreview() {
    LBNegativeDialogButton(
        textRes = R.string.cancel,
        onClick = { }
    )
}
