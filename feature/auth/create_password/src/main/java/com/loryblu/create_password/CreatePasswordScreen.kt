package com.loryblu.create_password

 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.height
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.layout.width
 import androidx.compose.material3.Icon
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.LaunchedEffect
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.saveable.rememberSaveable
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.res.stringResource
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.dp
 import androidx.lifecycle.compose.collectAsStateWithLifecycle
 import com.loryblu.ui.P_SMALL
 import com.loryblu.ui.components.LBButton
 import com.loryblu.ui.components.LBPasswordTextField
 import com.loryblu.util.validators.PasswordInputValid
 import com.loryblu.ui.R

@Composable
fun CreatePasswordScreen(
    viewModel: CreatePasswordViewModel,
    navigateToLoginScreen: () -> Unit,
    onResetPasswordButtonClicked: () -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(P_SMALL)
            .fillMaxSize()
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        var passwordHidden by rememberSaveable { mutableStateOf(true) }
        var confirmPasswordHidden by rememberSaveable { mutableStateOf(true) }



        com.loryblu.ui.components.LBTitle(textRes = R.string.create_a_new_password)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            stringResource(R.string.reset_your_password_here),
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // password
        LBPasswordTextField(
            onValueChange = { newPassword: String ->
                viewModel.run {
                    updatePassword(newPassword = newPassword)
                    passwordCheck()
                    verifyConfirmationPassword()
                }
            },
            onButtonClick = { passwordHidden = !passwordHidden },
            labelRes = stringResource(id = R.string.new_password),
            value = uiState.password,
            error = PasswordInputValid.Empty,
            hidden = passwordHidden,
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(
                    P_SMALL
                )
                .fillMaxWidth()
        ) {
            var counter = true
            for (element in uiState.passwordErrors) {
                counter = element.value and counter
            }

            // test if the counter is true and this means that every field has the requirement
            if (counter.not()) {
                Text(
                    stringResource(R.string.the_password_must_have),
                    style = MaterialTheme.typography.labelMedium
                )
                uiState.passwordErrors.forEach {
                    if (!it.value) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                tint = com.loryblu.ui.theme.Error
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = stringResource(id = it.key),
                                color = com.loryblu.ui.theme.Error,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = stringResource(id = it.key),
                                color = Color.Black,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }


        LBPasswordTextField(
            onValueChange = { newConfirmationPassword: String ->
                viewModel.run {
                    updateConfirmationPassword(newConfirmationPassword)
                    verifyConfirmationPassword()
                }
            },
            onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
            labelRes = stringResource(id = R.string.repeat_password),
            value = uiState.confirmationPassword,
            error = uiState.confirmPasswordState,
            hidden = confirmPasswordHidden
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.warning_about_change_the_password),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(32.dp))

        LBButton(
            textRes = R.string.reset_password,
            onClick = {
                onResetPasswordButtonClicked()
            },
            modifier = Modifier
        )
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if(shouldGoToNextScreen) {
            navigateToLoginScreen()
        }
    }
}

@Preview
@Composable
fun PreviewCreatePasswordScreen() {
    CreatePasswordScreen(
        viewModel = CreatePasswordViewModel(),
        navigateToLoginScreen = {

        },
        onResetPasswordButtonClicked = {

        },
        shouldGoToNextScreen = false,
    )
}