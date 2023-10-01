package com.loryblu.loryblu.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.loryblu.R

@Composable
fun LBTitle(
    @StringRes textRes: Int
) {
    /**
     * Composable that creates logo and title
     */
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.loryblu_logo),
            modifier = Modifier
                .width(187.dp)
                .height(47.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Preview
@Composable
fun PreviewLBTitle() {
    LBTitle(R.string.confirm_password)
}