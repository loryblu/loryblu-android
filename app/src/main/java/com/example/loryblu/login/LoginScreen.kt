package com.example.loryblu.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.loryblu.R
import com.example.loryblu.ui.theme.fontH6
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    var email by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = R.drawable.logo), contentDescription = stringResource(R.string.loryblu_logo))
        Text(
            text = stringResource(R.string.login),
            style = fontH6()
        )
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            })

    }

}

@Composable
@Preview
fun PreviewComposable() {
    LoginScreen(viewModel = LoginViewModel())
}

