package com.example.loryblu.createpassword

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.theme.Error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen(
    viewModel: CreatePasswordViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    OutlinedTextField(
        value = uiState.password,
        onValueChange = { newPassword: String ->
            viewModel.run {
                updatePassword(newPassword = newPassword)
                passwordCheck(newPassword = newPassword)
            }
        },
        label = {
            Text(text = stringResource(R.string.password))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = stringResource(R.string.lock_icon)
            )
        },
        trailingIcon = {
            val painterIcon =
                if (uiState.showPassword)
                    painterResource(id = R.drawable.ic_eye_close)
                else
                    painterResource(id = R.drawable.ic_eye_open)

            val contentDescription =
                if (uiState.showPassword)
                    stringResource(R.string.close_eye)
                else
                    stringResource(R.string.open_eye)

            IconButton(onClick = { viewModel.togglePassword() }) {
                Icon(
                    painter = painterIcon,
                    contentDescription = contentDescription
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )

    // confirmation Password
    OutlinedTextField(
        value = uiState.confirmationPassword,
        onValueChange = { newConfirmationPassword: String ->
            viewModel.run {
                updateConfirmationPassword(newConfirmationPassword)
                verifyConfirmationPassword(newConfirmationPassword)
            }
        },
        label = {
            Text(text = stringResource(R.string.confirm_password))
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = stringResource(R.string.lock_icon)
            )
        },
        trailingIcon = {
            val painterIcon =
                if (uiState.showConfirmationPassword)
                    painterResource(id = R.drawable.ic_eye_close)
                else
                    painterResource(id = R.drawable.ic_eye_open)

            val contentDescription =
                if (uiState.showConfirmationPassword)
                    stringResource(R.string.close_eye)
                else
                    stringResource(R.string.open_eye)

            IconButton(onClick = { viewModel.toggleConfirmationPassword() }) {
                Icon(
                    painter = painterIcon,
                    contentDescription = contentDescription
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation =
        if (uiState.showConfirmationPassword)
            PasswordVisualTransformation('*')
        else
            VisualTransformation.None
    )

    if (uiState.equalsPassword == false) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.passwords_must_be_identical),
                color = Error,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}