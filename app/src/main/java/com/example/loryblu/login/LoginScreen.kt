package com.example.loryblu.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBEmailTextField
import com.example.loryblu.ui.components.LBPasswordTextField
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.ui.theme.DarkBlue
import com.example.loryblu.ui.theme.Error
import com.example.loryblu.util.P_LARGE
import com.example.loryblu.util.P_MEDIUM
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    authenticated: Boolean,
    onLoginButtonClicked: () -> Unit,
    navigateToGuardianRegister: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var passwordHidden by rememberSaveable { mutableStateOf(true) }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(P_SMALL)
    ) {
        LBTitle(textRes = R.string.login)

        Spacer(modifier = Modifier.height(16.dp))

        LBEmailTextField(
            onValueChange = { it: String ->
                viewModel.updateEmail(it)
                viewModel.emailState(email = it)
            },
            labelRes = stringResource(R.string.email),
            value = uiState.email,
            error = uiState.emailProblem,
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(P_MEDIUM)
        )

        LBPasswordTextField(
            onValueChange = { newPassword: String  -> viewModel.run {
                updatePassword(newPassword)
            }},
            onButtonClick = { passwordHidden = !passwordHidden },
            labelRes = R.string.password,
            value = uiState.password,
            hidden = passwordHidden
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(P_MEDIUM)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(P_MEDIUM)
        ) {
            Checkbox(checked = uiState.isLoginSaved, onCheckedChange = { viewModel.toggleIsLoginSaved() })
            Spacer(modifier = Modifier.weight(2f))
            Text(
                text = stringResource(R.string.remember),
                color = Color.Black,
                modifier = Modifier.weight(2f),
                maxLines = 1
            )
//            Text(
//                text = stringResource(uiState.emailProblem),
//                modifier = Modifier.weight(2f),
//                maxLines = 1,
//                color = Error,
//                style = MaterialTheme.typography.labelLarge
//            )
        }
        LBButton(
            textRes = R.string.login,
            onClick = { onLoginButtonClicked() },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(P_LARGE))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier.weight(4f),
                color = Color.Black,
                thickness = 2.dp
            )
            Text(
                text = stringResource(R.string.or),
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(
                modifier = Modifier.weight(4f),
                color = Color.Black,
                thickness = 2.dp
            )
        }
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(top = 4.dp)) {
            Spacer(modifier = Modifier.weight(6f))
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.weight(4f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(54.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = stringResource(R.string.google_logo),
                modifier = Modifier.weight(1f)
            )
//            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                contentDescription = stringResource(R.string.facebook_logo),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.don_t_have_an_account),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = stringResource(R.string.register_now),
                style = MaterialTheme.typography.bodyLarge,
                color = DarkBlue,
                textDecoration = TextDecoration.Underline
            )
        }

    }

    LaunchedEffect(key1 = authenticated) {
        if(authenticated) {
            navigateToGuardianRegister()
        }
    }
}

@Composable
@Preview
fun PreviewComposable() {
    LoginScreen(
        viewModel = LoginViewModel(),
        authenticated = false,
        onLoginButtonClicked = {

        },
        navigateToGuardianRegister = {

        }
    )
}

