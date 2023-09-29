package com.example.loryblu.forgotpassword

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBEmailTextField
import com.example.loryblu.ui.components.LBSuccessLabel
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.util.P_MEDIUM
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
    authenticated: Boolean,
    sendEmailSuccess: Boolean,
    navigateToCreatePasswordScreen: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val showSuccessLabel = remember { mutableStateOf(false) }
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
            text = stringResource(R.string.reset_your_password_here),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(64.dp))

        LBEmailTextField(
            onValueChange = { email: String ->
                viewModel.updateEmail(email)
                viewModel.emailState()
            },
            labelRes = stringResource(id = R.string.email),
            value = uiState.email,
            error = uiState.emailState,
        )

        Spacer(modifier = Modifier.height(P_MEDIUM))

        LBButton(
            textRes = R.string.send,
            onClick = {
                viewModel.sendEmail()
            }, modifier = Modifier
        )

        if(showSuccessLabel.value) {
            LBSuccessLabel(
                labelRes = stringResource(R.string.email_sent_successfully)
            )
        }
    }

    LaunchedEffect(key1 = authenticated) {
        if(authenticated) {
            Log.d("ForgotPasswordScreen", "Navigate to create password screen")
            navigateToCreatePasswordScreen()
        }
    }

    LaunchedEffect(key1 = sendEmailSuccess) {
        if(sendEmailSuccess) {
            Log.d("ForgotPasswordScreen", "Showing that email has been send successfully")
            showSuccessLabel.value = true
        }
    }
}


@Composable
@Preview
fun PreviewForgotScreen() {
    ForgotPasswordScreen(viewModel = ForgotPasswordViewModel(), authenticated = false, sendEmailSuccess = true, navigateToCreatePasswordScreen = {})
}