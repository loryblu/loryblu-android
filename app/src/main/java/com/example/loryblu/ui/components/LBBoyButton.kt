package com.example.loryblu.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loryblu.R

@Composable
fun LBBoyButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier
            .width(168.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(end = 16.dp, start = 8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_boy),
            contentDescription = stringResource(id = R.string.boy_icon),
            tint = Color.LightGray,
            modifier = Modifier.padding(end = 16.dp),
        )
        Text(
            text = stringResource(textRes),
            color = Color.LightGray,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            modifier = Modifier.width(95.dp).padding(end = 16.dp),
        )

    }
}

@Composable
@Preview
fun PreviewLBBoyButton() {
    LBBoyButton(
        textRes = 0, onClick = {}, modifier = Modifier
    )
}