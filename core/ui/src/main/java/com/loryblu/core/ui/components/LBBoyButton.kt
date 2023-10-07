package com.loryblu.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.models.GenderInput

@Composable
fun LBBoyButton(
    onClick: () -> Unit,
    modifier: Modifier,
    genderInput: GenderInput,

    ) {
    val boySelected = (genderInput == GenderInput.MALE)
    val isError = (genderInput == GenderInput.Error)
    val buttonBorderColor = if (!isError) Color(0xff004a98) else MaterialTheme.colorScheme.error

    OutlinedButton(
        onClick = { onClick()},
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(end = 16.dp, start = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (boySelected) Color.White else Color.LightGray,
            containerColor = if (boySelected) Color(0xff004a98) else Color.Transparent,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = buttonBorderColor,
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_boy),
            contentDescription = stringResource(id = R.string.boy_icon),
            tint = if (boySelected) Color.White else Color.LightGray,
            modifier = Modifier.padding(end = 16.dp),
        )
        Text(
            text = stringResource(R.string.boy),
            color = if (boySelected) Color.White else Color.LightGray,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}
@Composable
@Preview
fun PreviewLBBoyButton() {
    LBBoyButton(
        onClick = {}, modifier = Modifier, genderInput = GenderInput.MALE
    )
}