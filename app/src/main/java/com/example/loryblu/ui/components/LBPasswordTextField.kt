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

//
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LBPasswordTextField(
    show: Boolean,
    // estou passando os resources para ter que transformar resources em valores usaveis apenas uma vez
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    labelRes: Int,
    value: String
) {
    val visualTransformation =
        if (show)
            PasswordVisualTransformation('*')
        else
            VisualTransformation.None

    val trailingIconRes =
        if (show)
            R.drawable.ic_eye_close
        else
            R.drawable.ic_eye_open

    val trailingDescriptionRes =
        if (show)
            R.string.close_eye
        else
            R.string.open_eye

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = stringResource(labelRes))
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIconRes),
                contentDescription = stringResource(id = trailingDescriptionRes)
            )
        },
        leadingIcon = {
            IconButton(onClick = { onButtonClick.invoke() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_lock),
                    contentDescription = stringResource(R.string.lock_icon)
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation
    )
}