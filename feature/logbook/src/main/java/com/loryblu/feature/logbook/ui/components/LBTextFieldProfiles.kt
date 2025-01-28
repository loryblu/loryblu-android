package com.loryblu.feature.logbook.ui.components

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.ui.utils.LBPreview
import com.loryblu.core.util.validators.InputValid

@Composable
fun LBTextFieldProfiles(
    value: String,
    onValueChange: (String) -> Unit,
    @DrawableRes icon: Int,
    placeholderRes: String,
    error: InputValid,
    fieldFocus: (Boolean) -> Unit = {},
    isEditable: Boolean = false,
) {
    val textFieldContainerColor = if (isEditable) LBSoftBlue else LBLightGray.copy(alpha = 0.6f)
    val textFieldIconColor = if (isEditable) LBDarkBlue else LBMediumGray

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                painterResource(id = icon),
                contentDescription = null,
                tint = textFieldIconColor
            )
        },
        placeholder = {
            Text(
                text = placeholderRes,
                color = LBShadowGray,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        },
        trailingIcon = {
            if (!isEditable) {
                Icon(
                    painterResource(id = R.drawable.ic_padlock_close),
                    contentDescription = null,
                    tint = LBDarkBlue,
                )
            }
        },
        textStyle = TextStyle(
            color = LBShadowGray,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        singleLine = true,
        isError = error is InputValid.Error,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                fieldFocus(focusState.isFocused)
            },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = LBSilverGray,
            focusedBorderColor = textFieldContainerColor,
            unfocusedBorderColor = textFieldContainerColor,
            disabledBorderColor = textFieldContainerColor,
            errorContainerColor = textFieldContainerColor,
            disabledContainerColor = textFieldContainerColor,
            focusedContainerColor = textFieldContainerColor,
            unfocusedContainerColor = textFieldContainerColor,
        )
    )
}

@LBPreview
@Composable
private fun LBNameTextFieldPreview() {
    LBTextFieldProfiles(
        onValueChange = {},
        placeholderRes = "Nome",
        value = "",
        icon = R.drawable.ic_user,
        error = InputValid.Valid,
        fieldFocus = {}
    )
}

@LBPreview
@Composable
private fun LBNameTextFieldEditablePreview() {
    LBTextFieldProfiles(
        onValueChange = {},
        placeholderRes = "Nome",
        value = "",
        icon = R.drawable.ic_user,
        error = InputValid.Valid,
        fieldFocus = {},
        isEditable = true
    )
}
