package com.example.loryblu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.loryblu.R

// TODO seek problems in this function
/**
 * this function call the outilinedTextField for password and confirmationPassword
 * **/
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LBPasswordTextField(
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    labelRes: Int,
    value: String,
    show: Boolean
) {
    val visualTransformation =
        if (show)
            VisualTransformation.None
        else
            PasswordVisualTransformation('*')

    val trailingIconRes =
        if (show)
            R.drawable.ic_eye_open
        else
            R.drawable.ic_eye_close

    val trailingDescriptionRes =
        if (show)
            R.string.open_eye
        else
            R.string.close_eye

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = stringResource(labelRes))
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
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation
    )
}