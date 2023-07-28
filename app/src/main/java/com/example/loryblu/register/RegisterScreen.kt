package com.example.loryblu.register

import androidx.compose.foundation.Image
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
        modifier = Modifier.verticalScroll(rememberScrollState())
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
            // aqui tem um bug quando se trata da parte numerica da senha
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { newPassword: String ->
                    viewModel.updatePassword(newPassword = newPassword)
                    viewModel.passwordCheck(newPassword = newPassword)
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


                // possivelmente pode ter um problema de recomposição de ui mesmo mudando os valores do
                // passwordHas talvez não der um trigger para essa função de recomposição em que mude a UI
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