package com.loryblu.feature.auth.register.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBEmailTextField
import com.loryblu.core.ui.components.LBNameTextField
import com.loryblu.core.ui.components.LBPasswordTextField
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftGray
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
    var isNameFieldFocused by rememberSaveable { mutableStateOf(false) }
    var isEmailFieldFocused by rememberSaveable { mutableStateOf(false) }
    var isPasswordFieldFocused by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordFieldFocused by rememberSaveable { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf<NameInputValid>(NameInputValid.Empty) }
    var email by rememberSaveable { mutableStateOf("") }
    var emailState by rememberSaveable { mutableStateOf<EmailInputValid>(EmailInputValid.Empty) }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordState by rememberSaveable { mutableStateOf<PasswordInputValid>(PasswordInputValid.Empty) }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var confirmPasswordState by rememberSaveable {
        mutableStateOf<PasswordInputValid>(
            PasswordInputValid.Empty
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        LBTitle(textRes = R.string.guardian_registration)

        Spacer(modifier = Modifier.height(32.dp))

        LBNameTextField(
            value = name,
            onValueChange = { newName ->
                name = newName
                nameState = nameStateValidation(name)
            },
            placeholderRes = stringResource(id = R.string.full_name),
            error = nameState,
            fieldFocus = { isNameFieldFocused = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LBEmailTextField(
            onValueChange = { newEmail ->
                email = newEmail
                emailState = emailStateValidation(email)
            },
            placeholderRes = stringResource(R.string.email),
            value = email,
            error = emailState,
            fieldFocus = { isEmailFieldFocused = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        LBPasswordTextField(
            onValueChange = { newPass: String ->
                password = newPass
                passwordState = passwordStateValidation(password)
            },
            onButtonClick = {
                passwordHidden = !passwordHidden
            },
            placeholderRes = stringResource(id = R.string.password),
            value = password,
            error = passwordState,
            hidden = passwordHidden,
            fieldFocus = { isPasswordFieldFocused = it },
        )

        if (!isPasswordFieldFocused || passwordState !is PasswordInputValid.ErrorList) {
            Spacer(modifier = Modifier.height(16.dp))
        }

        /**
         * If any of the entries in passwordHas is false so it contain some error
         * So it will display the errors below
         */
        if (isPasswordFieldFocused) {
            if (passwordState is PasswordInputValid.ErrorList) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(R.string.the_password_must_have),
                        color = LBShadowGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

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
                                    tint = LBErrorColor
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(id = it.key),
                                    color = LBErrorColor,
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
                                    tint = LBShadowGray
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = stringResource(id = it.key),
                                    color = LBShadowGray,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }

        LBPasswordTextField(
            onValueChange = { newPassConfirm ->
                confirmPassword = newPassConfirm
                confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
            },
            onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
            placeholderRes = stringResource(id = R.string.confirm_password),
            value = confirmPassword,
            error = confirmPasswordState,
            hidden = confirmPasswordHidden,
            fieldFocus = { isConfirmPasswordFieldFocused = it },
        )

        if (
            emailState is EmailInputValid.Error && isEmailFieldFocused
            || confirmPasswordState is PasswordInputValid.Error && isConfirmPasswordFieldFocused
            || nameState is NameInputValid.Error && isNameFieldFocused
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                when {

                    emailState is EmailInputValid.Error && isEmailFieldFocused -> {
                        val emailError = emailState as EmailInputValid.Error

                        Text(
                            fontSize = 14.sp,
                            modifier = Modifier,
                            text = stringResource(id = emailError.messageId),
                            fontWeight = FontWeight.Bold,
                            color = LBErrorColor
                        )
                    }

                    confirmPasswordState is PasswordInputValid.Error && isConfirmPasswordFieldFocused -> {
                        val passwordError = confirmPasswordState as PasswordInputValid.Error

                        Text(
                            fontSize = 14.sp,
                            modifier = Modifier,
                            text = stringResource(id = passwordError.messageId),
                            fontWeight = FontWeight.Bold,
                            color = LBErrorColor
                        )
                    }

                    nameState is NameInputValid.Error && isNameFieldFocused -> {
                        val nameError = nameState as NameInputValid.Error

                        Text(
                            fontSize = 14.sp,
                            modifier = Modifier,
                            text = stringResource(id = nameError.messageId),
                            fontWeight = FontWeight.Bold,
                            color = LBErrorColor
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        LBButton(
            areAllFieldsValid = nameState is NameInputValid.Valid
                    && emailState is EmailInputValid.Valid
                    && passwordState is PasswordInputValid.Valid
                    && confirmPasswordState is PasswordInputValid.Valid,
            textRes = R.string.next,
            onClick = {
                nameState = nameStateValidation(name)
                passwordState = passwordStateValidation(password)
                confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
                emailState = emailStateValidation(email)
                if (
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
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = LBLightGray,
                containerColor = LBSkyBlue
            ),
            textColor = if (
                nameState is NameInputValid.Valid
                && emailState is EmailInputValid.Valid
                && passwordState is PasswordInputValid.Valid
                && confirmPasswordState is PasswordInputValid.Valid
            ) LBSoftGray else LBSkyBlue
        )
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            navigateToChildRegister()
        }
    }
}