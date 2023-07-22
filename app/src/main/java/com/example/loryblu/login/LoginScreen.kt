package com.example.loryblu.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.theme.Black
import com.example.loryblu.ui.theme.fontH6
import com.example.loryblu.util.P_MEDIUM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painter = painterResource(id = R.drawable.logo), contentDescription = stringResource(R.string.loryblu_logo))
        Text(
            text = stringResource(R.string.login),
            style = fontH6()
        )
        // isso esta bugado por que esta faltando um parametro
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { it: String -> viewModel.updateEmail(it) },
            leadingIcon = {
                Icon(
                    painterResource(id = R.drawable.ic_mail),
                    contentDescription = stringResource(R.string.mail_icon)
                )
            },
            label = { Text(text = stringResource(R.string.email)) } ,
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            modifier = Modifier
                .width(327.dp)
                .height(48.dp),

        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(P_MEDIUM))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::updatePassword,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = stringResource(R.string.password)
                )
            },
            label = { Text(text = stringResource(id = R.string.password)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            modifier = Modifier
                .width(327.dp)
                .height(48.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye_close),
                    contentDescription = "close eye"
                )
            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(P_MEDIUM))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(P_MEDIUM)
        ) {
            Switch(
                checked = uiState.isLoginSaved,
                onCheckedChange = viewModel::updateIsSaved,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.remember),
                color = Black,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = viewModel.printEmailProblem(),
                modifier = Modifier.weight(1f)
            )
        }



    }

}

@Composable
@Preview
fun PreviewComposable() {
    LoginScreen(viewModel = LoginViewModel())
}

