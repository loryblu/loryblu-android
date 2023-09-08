package com.example.loryblu.register.guardian

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.loryblu.R
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBPasswordTextField
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.ui.theme.Error
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuardianRegisterScreen(
    viewModel: GuardianRegisterViewModel,
    navigateToChildRegister: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var confirmPasswordHidden by rememberSaveable { mutableStateOf(true) }

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
//            Spacer(modifier = Modifier.height(16.dp))
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
//            Spacer(modifier = Modifier.height(16.dp))

            // Password
           LBPasswordTextField(
               onValueChange = { newPass: String ->
                   viewModel.run {
                       updatePassword(newPass)
                       passwordCheck(newPass)
                   }
               },
               onButtonClick = {
                   passwordHidden = !passwordHidden
               },
               labelRes = stringResource(id = R.string.password),
               value = uiState.password,
               error = uiState.passwordState,
               hidden = passwordHidden
           )
//            Spacer(modifier = Modifier.height(16.dp))
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
                for (element in uiState.passwordHas.entries) {
                    counter = element.value and counter
                }

                // test if the counter is true and this means that every field has the requirement
                if (counter.not()) {
                    Text(
                        stringResource(R.string.the_password_must_be),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                uiState.passwordHas.forEach {
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
//            Spacer(modifier = Modifier.height(16.dp))
            // confirmation password
           LBPasswordTextField(
               onValueChange = { newPassConfir ->
                   viewModel.run{
                       updateConfirmationPassword(newPassConfir)
                       verifyConfirmationPassword(newPassConfir)
                   }
               },
               onButtonClick = { confirmPasswordHidden = !confirmPasswordHidden },
               labelRes = stringResource(id = R.string.confirm_password),
               value = uiState.confirmationPassword,
               error = uiState.passwordState,
               hidden = confirmPasswordHidden,
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
//        Spacer(modifier = Modifier.height(32.dp))
        LBButton(
            textRes = R.string.next,
            onClick = {
                navigateToChildRegister()
            },
            modifier = Modifier
        )
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    GuardianRegisterScreen(
        viewModel = GuardianRegisterViewModel(),
        navigateToChildRegister = {

        }
        )
}