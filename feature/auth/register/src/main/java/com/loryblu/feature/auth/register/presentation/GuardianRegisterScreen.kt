package com.loryblu.feature.auth.register.presentation

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
import com.loryblu.core.ui.P_SMALL
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBEmailTextField
import com.loryblu.core.ui.components.LBPasswordTextField
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.theme.Error
import com.loryblu.core.util.validators.EmailInputValid
import com.loryblu.core.util.validators.NameInputValid
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.feature.auth.register.model.Guardian

@Composable
fun GuardianRegisterScreen(
    navigateToChildRegister: () -> Unit,
    onNextButtonClicked: (Guardian) -> Unit,
    shouldGoToNextScreen: Boolean,
    nameStateValidation: (name: String) -> NameInputValid,
    emailStateValidation: (email: String) -> EmailInputValid,
    passwordStateValidation: (password: String) -> PasswordInputValid,
    confirmPasswordStateValidation: (password: String, confirmPassword: String) -> PasswordInputValid,
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var confirmPasswordHidden by rememberSaveable { mutableStateOf(true) }
    var isPasswordTextFieldSelected by rememberSaveable { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf<NameInputValid>(NameInputValid.Empty) }
    var email by rememberSaveable { mutableStateOf("") }
    var emailState by rememberSaveable { mutableStateOf<EmailInputValid>(EmailInputValid.Empty) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf<PasswordInputValid>(PasswordInputValid.Empty) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordState by rememberSaveable { mutableStateOf<PasswordInputValid>(PasswordInputValid.Empty) }

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
                value = name,
                onValueChange = { newName ->
                    name = newName
                    nameState = nameStateValidation(name)
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
                isError = nameState is NameInputValid.Error,
                supportingText = {
                    if(nameState is NameInputValid.Error) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = (nameState as NameInputValid.Error).messageId),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
            )
            LBEmailTextField(
                onValueChange = { newEmail ->
                    email = newEmail
                    emailState = emailStateValidation(email)
                },
                labelRes = stringResource(R.string.email),
                value = email,
                error = emailState,
            )

            // Password field
            LBPasswordTextField(
                onValueChange = { newPass: String ->
//                    viewModel.run {
//                        updatePassword(newPass)
//                        passwordState(newPass)
//                    }
                    password = newPass
                    passwordState = passwordStateValidation(password)
                },
                onButtonClick = {
                    passwordHidden = !passwordHidden
                },
                labelRes = stringResource(id = R.string.password),
                value = password,
                error = passwordState,
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
                if (passwordState is PasswordInputValid.ErrorList) {
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
                        (passwordState as PasswordInputValid.ErrorList).errors.forEach {
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
                                        tint = Error
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = stringResource(id = it.key),
                                        color = Error,
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
                    confirmPassword = newPassConfirm
                    confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
                },
                onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
                labelRes = stringResource(id = R.string.confirm_password),
                value = confirmPassword,
                error = confirmPasswordState,
                hidden = confirmPasswordHidden,
            )
        }
        LBButton(
            textRes = R.string.next,
            onClick = {
                nameState = nameStateValidation(name)
                passwordState = passwordStateValidation(password)
                confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
                emailState = emailStateValidation(email)
                if(
                    nameState == NameInputValid.Valid
                    && passwordState == PasswordInputValid.Valid
                    && confirmPasswordState == PasswordInputValid.Valid
                    && emailState == EmailInputValid.Valid
                ) {
                    onNextButtonClicked(
                        Guardian(
                            email = email,
                            password = password,
                            name = name
                        )
                    )
                }
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
        navigateToChildRegister = {

        },
        onNextButtonClicked = {

        },
        shouldGoToNextScreen = false,
        passwordStateValidation = {
            PasswordInputValid.Valid
        },
        confirmPasswordStateValidation = { password, confirmPassword ->
            PasswordInputValid.Valid
        },
        emailStateValidation = {
            EmailInputValid.Valid
        },
        nameStateValidation = {
            NameInputValid.Valid
        }
    )
}