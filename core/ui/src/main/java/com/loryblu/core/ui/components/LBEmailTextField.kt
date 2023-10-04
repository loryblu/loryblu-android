package com.loryblu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.loryblu.core.ui.R
import com.loryblu.core.util.validators.EmailInputValid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LBEmailTextField(
    onValueChange: (String) -> Unit,
    labelRes: String,
    value: String,
    error: EmailInputValid,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_email),
                contentDescription = stringResource(R.string.email_icon)
            )
        },
        label = { Text(text = labelRes) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        isError = error is EmailInputValid.Error,
        supportingText = {
            if(error is EmailInputValid.Error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = error.messageId),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}