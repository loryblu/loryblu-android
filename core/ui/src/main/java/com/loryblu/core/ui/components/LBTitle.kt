package com.loryblu.core.ui.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBShadowGray

@Composable
fun LBTitle(
    @StringRes textRes: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_loryblu_logo),
            contentDescription = stringResource(R.string.loryblu_logo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(187.dp)
                .height(47.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = LBShadowGray
        )
    }
}