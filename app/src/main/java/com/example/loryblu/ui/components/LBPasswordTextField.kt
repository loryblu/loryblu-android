package com.example.loryblu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.loryblu.R
import com.example.loryblu.login.PasswordInputValid

// TODO seek problems in this function
/**
 * this function call the outilinedTextField for password and confirmationPassword
 * **/
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LBPasswordTextField(
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    labelRes: String,
    value: String,
    error: PasswordInputValid,
    hidden: Boolean
) {
    val visualTransformation =
        if (hidden) PasswordVisualTransformation() else VisualTransformation.None

    val trailingIconRes =
        if (hidden) R.drawable.ic_eye_close else R.drawable.ic_eye_open

    val trailingDescriptionRes =
        if (hidden) R.string.close_eye else R.string.open_eye

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = {
            Text(text = labelRes)
        },
        trailingIcon = {
            IconButton(onClick = { onButtonClick() }) {
                Icon(
                    painter = painterResource(id = trailingIconRes),
                    contentDescription = stringResource(id = trailingDescriptionRes)
                )
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_lock),
                contentDescription = stringResource(R.string.lock_icon)
            )
        },
        isError = error is PasswordInputValid.Error,
        supportingText = {
            if(error is PasswordInputValid.Error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(error.messageId),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
}