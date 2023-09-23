package com.example.loryblu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loryblu.R
import com.example.loryblu.util.NameInputValid

@Composable
fun LBNameTextField(
    value : String,
    onValueChange: (String) -> Unit,
    labelRes: String,
    error: NameInputValid,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_user),
                contentDescription = stringResource(R.string.name_icon)
            )
        },
        label = { Text(text = labelRes) },
        singleLine = true,
        isError = error is NameInputValid.Error,
        supportingText = {
            if(error is NameInputValid.Error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = error.messageId),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier.width(352.dp),
        shape = RoundedCornerShape(10.dp),
    )
}

@Composable
@Preview
fun LBNameTextFieldPreview() {
    LBNameTextField(
        value = "",
        onValueChange = {},
        labelRes = "Name",
        error = NameInputValid.Empty,
    )
}