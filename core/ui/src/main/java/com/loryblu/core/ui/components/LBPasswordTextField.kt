package com.loryblu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.util.validators.PasswordInputValid

/**
 * this function call the outilinedTextField for password and confirmationPassword
 * **/
@Composable
fun LBPasswordTextField(
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    placeholderRes: String,
    value: String,
    error: PasswordInputValid,
    hidden: Boolean,
    fieldFocus: (Boolean) -> Unit = {},
) {
    val visualTransformation =
        if (hidden) PasswordVisualTransformation() else VisualTransformation.None

    val trailingIconRes =
        if (hidden) R.drawable.ic_eye_close else R.drawable.ic_eye_open

    val trailingDescriptionRes =
        if (hidden) R.string.hide_password_icon else R.string.show_password_icon

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = {
            Text(
                text = placeholderRes,
                color = LBSilverGray,
                fontWeight = FontWeight.Bold
            )
        },
        textStyle = TextStyle(
            color = LBSilverGray,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ),
        trailingIcon = {
            IconButton(
                modifier = Modifier.padding(end = 18.dp),
                onClick = { onButtonClick() }) {
                Icon(
                    painter = painterResource(id = trailingIconRes),
                    contentDescription = stringResource(id = trailingDescriptionRes),
                    tint = LBSilverGray,
                )
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_lock),
                contentDescription = stringResource(R.string.password_icon),
                tint = LBSilverGray,
            )
        },
        isError = error is PasswordInputValid.ErrorList || error is PasswordInputValid.Error,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                fieldFocus(focusState.isFocused)
            },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = LBSilverGray,
            focusedBorderColor = LBSoftBlue,
            unfocusedBorderColor = LBSoftBlue,
            disabledBorderColor = LBSoftBlue,
            errorContainerColor = LBSoftBlue,
            disabledContainerColor = LBSoftBlue,
            focusedContainerColor = LBSoftBlue,
            unfocusedContainerColor = LBSoftBlue,
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}