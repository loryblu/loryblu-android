package com.loryblu.feature.logbook.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.components.buttons.LBButton
import com.loryblu.core.ui.components.text_fields.LBPasswordTextField
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.White
import com.loryblu.core.ui.utils.LBPreview
import com.loryblu.core.util.validators.PasswordInputValid
import com.loryblu.feature.home.R

@Composable
fun ChangePasswordScreen() {
    ChangePasswordContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordContent(
    onBackButtonClicked: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.change_password),
                onBackClicked = { onBackButtonClicked() },
                showCloseButton = false
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                LogoAndTitle()
                Spacer(modifier = Modifier.height(48.dp))
                PasswordTextFields()
                Spacer(modifier = Modifier.weight(1f))
                AlertAccountWillDisconnect()
                Spacer(modifier = Modifier.height(12.dp))
                ResetPasswordButton()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    )
}

@Composable
fun LogoAndTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(24.dp),
        modifier = Modifier
            .padding(horizontal = 94.dp)
    ) {
        Image(
            painterResource(com.loryblu.core.ui.R.drawable.ic_loryblu_2),
            contentDescription = "Loryblu logo"
        )
        Text(
            text = "Redefina sua senha aqui",
            fontSize = 16.sp
        )
    }

}

@Composable
private fun PasswordTextFields() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(36.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        LBPasswordTextField(
            onValueChange = {},
            placeholderRes = "Senha antiga",
            value = "",
            error = PasswordInputValid.Valid,
            hidden = true,
            onButtonClick = {},
            fieldFocus = {}
        )
        LBPasswordTextField(
            onValueChange = {},
            placeholderRes = "Senha nova",
            value = "",
            error = PasswordInputValid.Valid,
            hidden = true,
            onButtonClick = {},
            fieldFocus = {}
        )
        LBPasswordTextField(
            onValueChange = {},
            placeholderRes = "Confirmar nova senha",
            value = "",
            error = PasswordInputValid.Valid,
            hidden = true,
            onButtonClick = {},
            fieldFocus = {}
        )
    }
}

@Composable
fun AlertAccountWillDisconnect() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_alert),
            contentDescription = "Aviso",
            tint = LBDarkBlue,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "Sua conta será desconectada e você precisará entrar com sua nova senha.",
            color = LBDarkBlue,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ResetPasswordButton() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        LBButton(
            textRes = R.string.reset_your_password,
            onClick = {},
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = LBLightGray,
                containerColor = LBSkyBlue
            ),
            areAllFieldsValid = true,
            textColor = White
        )
    }
}


@LBPreview
@Composable
private fun ChangePasswordPreview() {
    ChangePasswordContent()
}
