package com.loryblu.feature.auth.register.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBDarkBlue
import kotlinx.coroutines.delay

@Composable
fun RegistrationConfirmedScreen(
    navigateToHomeScreen: () -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    Box(
        modifier = Modifier
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 30.dp)
        )
        {
            Text(
                modifier = Modifier
                    .padding(end = 23.dp)
                    .clickable(enabled = true, onClick = {
                        navigateToHomeScreen()
                    }),
                fontSize = 20.sp,
                text = "X",
                color = LBDarkBlue
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                fontSize = 20.sp,
                text = stringResource(id = R.string.registration_ok),
                color = Color.Black,
            )
            Image(
                modifier = Modifier.padding(top = 70.dp),
                painter = painterResource(id = R.drawable.ic_ok_registration),
                contentDescription = stringResource(id = R.string.registration_ok_icon),
                contentScale = ContentScale.None
            )
        }
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            delay(3000)
            navigateToHomeScreen()
        }
    }
}