package com.example.loryblu.forgotpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.ui.theme.Error
import com.example.loryblu.util.P_MEDIUM
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
    navigateToNextScreen: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(P_SMALL)
            .fillMaxSize()
    ) {

        LBTitle(textRes = R.string.forgot_password)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.reset_password),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(64.dp))

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { newEmail: String ->
                viewModel.updateEmail(newEmail)
                viewModel.emailState(newEmail)
            },
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.ic_email),
                    contentDescription = stringResource(id = R.string.mail_icon)
                )
            },
            label = { Text(text = stringResource(R.string.email)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // se o id do problema for null isso siginifica que esta carregando a procura de erros no e-mail
        viewModel.idEmailProblem()?.run {
            Text(
                text = stringResource(id = this@run),
                style = MaterialTheme.typography.labelMedium,
                color = Error
            )
        } ?: run {
            // TODO
            // fazer uma animação de carregamento
        }

        Spacer(modifier = Modifier.height(P_MEDIUM))

        LBButton(
            textRes = R.string.send,
            onClick = {
                if (uiState.emailState == EmailState.NONE) {
                    navigateToNextScreen()

                }
            }, modifier = Modifier
        )
    }
}

@Composable
@Preview
fun PreviewForgotScreen() {
    ForgotPasswordScreen(viewModel = ForgotPasswordViewModel(), navigateToNextScreen = {})
}