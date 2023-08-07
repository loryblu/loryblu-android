package com.example.loryblu.createpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.theme.Blue
import com.example.loryblu.ui.theme.Error
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordScreen(
    viewModel: CreatePasswordViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(P_SMALL)
            .fillMaxSize()
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.loryblu_logo),
            modifier = Modifier
                .width(187.dp)
                .height(47.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Create a new password", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.reset_your_password_here),
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.warning_about_change_the_password),
            fontStyle = MaterialTheme.typography.labelMedium.fontStyle
        )

        Spacer(modifier = Modifier.height(32.dp))

        // TODO unify the buttons style in one single composable

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = ButtonDefaults.buttonColors(
                Blue
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.reset_password),
                color = Color.White
            )
        }

    }
}
