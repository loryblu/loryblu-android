package com.loryblu.loryblu.register.registration

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.loryblu.R
import com.loryblu.loryblu.ui.theme.DarkBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegistrationConfirmedScreen(
    navigateToHomeScreen: () -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
                color = DarkBlue
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Text(
                fontSize = 20.sp,
                text = "Cadastro efetuado com sucesso!",
                color = Color.Black,
            )
            Image(
                modifier = Modifier.padding(top = 70.dp),
                painter = painterResource(id = R.drawable.ic_ok_registration),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
        }
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            coroutineScope.launch {
                showToast(context, "Você será redirecionado em 3 segundos")
                delay(3000)
                navigateToHomeScreen()
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
@Preview
fun PreviewComposable() {
    RegistrationConfirmedScreen(
        navigateToHomeScreen = {},
        shouldGoToNextScreen = false,
    )
}