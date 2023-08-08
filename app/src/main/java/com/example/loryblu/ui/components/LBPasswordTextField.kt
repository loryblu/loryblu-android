package com.example.loryblu.ui.components

import android.util.Log
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
import com.example.loryblu.createpassword.UiStateCreatePassword

// TODO seek problems in this function
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LBPasswordTextField(
    uiState: UiStateCreatePassword,
    // estou passando os resources para ter que transformar resources em valores usaveis apenas uma vez
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    labelRes: Int,
    value: String
) {
    var trigger = false
    Log.e("button", "insideFucntion ${uiState.showPassword}")
    val visualTransformation =
        if (uiState.showPassword)
            VisualTransformation.None
        else
            PasswordVisualTransformation('*')

    val trailingIconRes =
        if (uiState.showPassword)
            R.drawable.ic_eye_open
        else
            R.drawable.ic_eye_close

    val trailingDescriptionRes =
        if (uiState.showPassword)
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
            Icon(
                painter = painterResource(id = trailingIconRes),
                contentDescription = stringResource(id = trailingDescriptionRes)
            )
        },
        leadingIcon = {
            Log.e("Button", "button clicked")
            IconButton(onClick = {
                // o problema é que mesmo clicando no botão ele não chama essa função então tem errado
                onButtonClick()
                trigger = true
                Log.e("Button", "trigger = $trigger")
                // essa parte do codigo nunca é alcançada ;-;
            }) {
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