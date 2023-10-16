package com.loryblu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.util.validators.EmailInputValid

@Composable
fun LBEmailTextField(
    onValueChange: (String) -> Unit,
    placeholderRes: String,
    value: String,
    error: EmailInputValid,
    fieldFocus: (Boolean) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_email),
                contentDescription = stringResource(R.string.email_icon),
                tint = LBSilverGray
            )
        },
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
        singleLine = true,
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
        isError = error is EmailInputValid.Error,
    )
}