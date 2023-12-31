package com.loryblu.feature.auth.create_password

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
import androidx.compose.runtime.remember
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
import com.loryblu.core.ui.components.LBErrorLabel
import com.loryblu.core.ui.components.LBPasswordTextField
import com.loryblu.core.ui.components.LBSuccessLabel
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.core.util.validators.PasswordInputValid
import kotlinx.coroutines.delay

@Composable
fun CreatePasswordScreen(
    viewModel: CreatePasswordViewModel,
    passwordStateValidation: (password: String) -> PasswordInputValid,
    confirmPasswordStateValidation: (password: String, confirmPassword: String) -> PasswordInputValid,
    navigateToLoginScreen: () -> Unit,
    onResetPasswordButtonClicked: (password: String) -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        var passwordHidden by rememberSaveable { mutableStateOf(true) }
        var passwordState by rememberSaveable {
            mutableStateOf<PasswordInputValid>(
                PasswordInputValid.Empty
            )
        }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPasswordHidden by rememberSaveable { mutableStateOf(true) }
        var isPasswordFieldFocused by remember { mutableStateOf(false) }
        var isConfirmPasswordFieldFocused by remember { mutableStateOf(false) }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var confirmPasswordState by rememberSaveable {
            mutableStateOf<PasswordInputValid>(
                PasswordInputValid.Empty
            )
        }
        val newPasswordMessage by rememberSaveable { mutableStateOf("") }

        LBTitle(textRes = R.string.create_a_new_password)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            stringResource(R.string.reset_your_password_here),
            style = MaterialTheme.typography.titleSmall,
            color = LBShadowGray
        )

        Spacer(modifier = Modifier.height(56.dp))

        // password
        LBPasswordTextField(
            onValueChange = { newPass: String ->
                password = newPass
                passwordState = passwordStateValidation(password)
            },
            onButtonClick = { passwordHidden = !passwordHidden },
            placeholderRes = stringResource(id = R.string.new_password),
            value = password,
            error = passwordState,
            hidden = passwordHidden,
            fieldFocus = { isPasswordFieldFocused = it },
        )

        if (!isPasswordFieldFocused || passwordState !is PasswordInputValid.ErrorList) {
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isPasswordFieldFocused) {
            if (passwordState is PasswordInputValid.ErrorList) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        stringResource(R.string.the_password_must_have),
                        style = MaterialTheme.typography.labelMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    (passwordState as PasswordInputValid.ErrorList).errors.forEach {
                        if (!it.value) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.padding(vertical = 5.dp)
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
                                modifier = Modifier.padding(vertical = 5.dp)
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
            onValueChange = { newPassConfirm: String ->
                confirmPassword = newPassConfirm
                confirmPasswordState = confirmPasswordStateValidation(password, confirmPassword)
            },
            onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
            placeholderRes = stringResource(id = R.string.repeat_password),
            value = confirmPassword,
            error = confirmPasswordState,
            hidden = confirmPasswordHidden,
            fieldFocus = {
                isConfirmPasswordFieldFocused = it
            },
        )

        if (
            confirmPasswordState is PasswordInputValid.Error
            && isConfirmPasswordFieldFocused
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                val emailError = confirmPasswordState as PasswordInputValid.Error

                Text(
                    fontSize = 14.sp,
                    modifier = Modifier,
                    text = stringResource(id = emailError.messageId),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
        }

        Text(
            text = stringResource(R.string.warning_about_change_the_password),
            style = MaterialTheme.typography.labelMedium,
            color = LBShadowGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 44.dp)
        )

        LBButton(
            areAllFieldsValid = passwordState is PasswordInputValid.Valid &&
                    confirmPasswordState is PasswordInputValid.Valid,
            textRes = R.string.reset_password,
            onClick = {
                onResetPasswordButtonClicked(password)
            },
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = LBLightGray,
                containerColor = LBSkyBlue
            ),
            textColor = if (
                passwordState is PasswordInputValid.Valid
                && confirmPasswordState is PasswordInputValid.Valid
            ) LBSoftGray else LBSkyBlue
        )

        if (viewModel.newPasswordSuccess.value) {
            LBSuccessLabel(
                labelRes = newPasswordMessage
            )
        } else {
            LBErrorLabel(
                labelRes = newPasswordMessage
            )
        }
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            delay(3000)
            navigateToLoginScreen()
        }
    }
}