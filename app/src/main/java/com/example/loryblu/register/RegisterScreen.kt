package com.example.loryblu.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loryblu.R
import com.example.loryblu.ui.theme.Blue
import com.example.loryblu.ui.theme.Error
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
    ) {
//        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.loryblu_logo),
            modifier = Modifier
                .width(187.dp)
                .height(47.dp)
        )
//        Spacer(modifier = Modifier.padding(32.dp))
        Text(
            text = stringResource(R.string.guardian_registration),
            style = MaterialTheme.typography.titleLarge
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(P_SMALL)
                .height(300.dp)
        ) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = viewModel::updateName,
                label = { Text(text = stringResource(R.string.name)) },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_user),
                        contentDescription = stringResource(
                            R.string.person_icon
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                label = { Text(text = stringResource(R.string.email)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = stringResource(
                            R.string.email_icon
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            // senha
            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
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

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(
                    P_SMALL
                )
            ) {
                Text(
                    stringResource(R.string.the_password_must_be),
                    style = MaterialTheme.typography.labelMedium
                )
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    uiState.passwordHas.forEach{
                        if (!it.value) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = null
                                )
                                Text(
                                    text = stringResource(id = it.key),
                                    color = Error,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }

            }
            // confirmação de senha
            OutlinedTextField(
                value = uiState.confirmationPassword,
                onValueChange = viewModel::updateConfirmationPassword,
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

        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                Blue
            )
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}


@Composable
@Preview
fun PreviewRegisterScreen() {
    RegisterScreen(viewModel = RegisterViewModel())
}