package com.loryblu.feature.auth.forgot_password

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBEmailTextField
import com.loryblu.core.ui.components.LBErrorLabel
import com.loryblu.core.ui.components.LBSuccessLabel
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.core.util.validators.EmailInputValid

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel,
    authenticated: Boolean,
    navigateToCreatePasswordScreen: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val showSuccessLabel = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        LBTitle(textRes = R.string.forgot_password)

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.reset_your_password_here),
            style = MaterialTheme.typography.titleSmall,
            color = LBShadowGray
        )

        Spacer(modifier = Modifier.height(56.dp))

        LBEmailTextField(
            onValueChange = { email: String ->
                viewModel.updateEmail(email)
                viewModel.emailState()
            },
            placeholderRes = stringResource(id = R.string.email),
            value = uiState.email,
            error = uiState.emailState,
        )

        Spacer(modifier = Modifier.height(44.dp))

        LBButton(
            areAllFieldsValid = uiState.emailState is EmailInputValid.Valid,
            textRes = R.string.send,
            onClick = {
                viewModel.sendEmail()
            },
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = LBLightGray,
                containerColor = LBSkyBlue
            ),
            textColor = if (
                uiState.emailState is EmailInputValid.Valid
            ) LBSoftGray else LBSkyBlue
        )

        if (uiState.emailState is EmailInputValid.Error) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            ) {

                val emailError = uiState.emailState as EmailInputValid.Error

                Text(
                    fontSize = 14.sp,
                    modifier = Modifier,
                    text = stringResource(id = emailError.messageId),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
        }

        when {
            showSuccessLabel.value && uiState.emailState !is EmailInputValid.Error -> LBSuccessLabel(
                labelRes = stringResource(R.string.email_sent_successfully)
            )

            viewModel.sendEmailSuccess.value && uiState.emailState !is EmailInputValid.Error -> LBSuccessLabel(
                labelRes = uiState.emailMessage
            )

            !viewModel.sendEmailSuccess.value && uiState.emailState !is EmailInputValid.Error -> LBErrorLabel(
                labelRes = uiState.emailMessage
            )
        }
    }

    LaunchedEffect(key1 = authenticated) {
        if (authenticated) {
            Log.d("ForgotPasswordScreen", "Navigate to create password screen")
            navigateToCreatePasswordScreen()
        }
    }
}