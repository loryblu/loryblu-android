package com.loryblu.register.guardian

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.ui.P_SMALL
import com.loryblu.ui.R
import com.loryblu.ui.components.LBEmailTextField
import com.loryblu.ui.components.LBPasswordTextField
import com.loryblu.ui.components.LBTitle
import com.loryblu.util.validators.NameInputValid

@Composable
fun GuardianRegisterScreen(
    viewModel: GuardianRegisterViewModel,
    navigateToChildRegister: () -> Unit,
    onNextButtonClicked: () -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var confirmPasswordHidden by rememberSaveable { mutableStateOf(true) }
    var isPasswordTextFieldSelected by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        LBTitle(textRes = R.string.guardian_registration)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(P_SMALL)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = {
                    viewModel.updateName(it)
                    viewModel.nameState()
                },
                label = { Text(text = stringResource(R.string.name)) },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_user),
                        contentDescription = stringResource(
                            R.string.name_icon
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = uiState.nameState is NameInputValid.Error,
                supportingText = {
                    if(uiState.nameState is NameInputValid.Error) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = (uiState.nameState as NameInputValid.Error).messageId),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
            )

            // Mudar para esse quando configurarmos o layout
//            LBNameTextField(
//                value = uiState.name,
//                onValueChange = { name: String ->
//                    viewModel.updateName(name)
//                    viewModel.nameState()
//                },
//                labelRes = stringResource(id = R.string.name),
//                error = uiState.nameState,
//            )
//            OutlinedTextField(
//                value = uiState.email,
//                onValueChange = viewModel::updateEmail,
//                label = { Text(text = stringResource(R.string.email)) },
//                leadingIcon = {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_email),
//                        contentDescription = stringResource(
//                            R.string.email_icon
//                        )
//                    )
//                },
//                modifier = Modifier.fillMaxWidth()
//            )
            LBEmailTextField(
                onValueChange = {
                    viewModel.updateEmail(it)
                    viewModel.emailState()
                },
                labelRes = stringResource(R.string.email),
                value = uiState.email,
                error = uiState.emailState,
            )

            // Password field
            LBPasswordTextField(
                onValueChange = { newPass: String ->
                    viewModel.run {
                        updatePassword(newPass)
                        passwordState()
                    }
                },
                onButtonClick = {
                    passwordHidden = !passwordHidden
                },
                labelRes = stringResource(id = R.string.password),
                value = uiState.password,
                error = uiState.passwordState,
                hidden = passwordHidden,
                fieldFocus = {
                    isPasswordTextFieldSelected = it
                },
            )

            /**
             * If any of the entries in passwordHas is false so it contain some error
             * So it will display the errors below
             */
            if(isPasswordTextFieldSelected) {
                if (false in uiState.passwordHas.values) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .padding(
                                P_SMALL
                            )
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.the_password_must_have)
                        )
                        uiState.passwordHas.forEach {
                            // It value is the entry, if some error so its false, if there is no error so its true
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
            }

            // Confirm password field
            LBPasswordTextField(
                onValueChange = { newPassConfirm ->
                    viewModel.run {
                        updateConfirmationPassword(newPassConfirm)
                        confirmPasswordState()
                    }
                },
                onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
                labelRes = stringResource(id = R.string.confirm_password),
                value = uiState.confirmationPassword,
                error = uiState.confirmPasswordState,
                hidden = confirmPasswordHidden,
            )
        }
        com.loryblu.ui.components.LBButton(
            textRes = R.string.next,
            onClick = {
                onNextButtonClicked()
            },
            modifier = Modifier
        )

        LaunchedEffect(key1 = shouldGoToNextScreen) {
            if(shouldGoToNextScreen) {
                navigateToChildRegister()
            }
        }
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    GuardianRegisterScreen(
        viewModel = GuardianRegisterViewModel(),
        navigateToChildRegister = {

        },
        onNextButtonClicked = {

        },
        shouldGoToNextScreen = false,
        )
}