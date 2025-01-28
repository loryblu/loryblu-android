package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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

@Composable
fun LBPasswordFieldProfiles(
    isEditable: Boolean = false,
    onPasswordChangeClicked: () -> Unit = {},
) {
    val textFieldContainerColor = if (isEditable) LBSoftBlue else LBLightGray.copy(alpha = 0.6f)
    val textFieldIconColor = if (isEditable) LBDarkBlue else LBMediumGray

    OutlinedTextField(
        enabled = isEditable,
        value = "",
        onValueChange = { },
        leadingIcon = {
            Icon(
                painterResource(id = com.loryblu.feature.home.R.drawable.ic_padlock_open),
                contentDescription = null,
                tint = textFieldIconColor
            )
        },
        placeholder = {
            Text(
                text = "⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤ ⬤",
                color = LBSilverGray,
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
            } else {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = androidx.compose.ui.text.SpanStyle(
                                color = LBDarkBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Alterar senha")
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { onPasswordChangeClicked }
                )
            }
        },
        textStyle = TextStyle(
            color = LBShadowGray,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
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
private fun LBPasswordFieldEditablePreview() {
    LBPasswordFieldProfiles(
        isEditable = true
    )
}

@LBPreview
@Composable
private fun LBPasswordFieldNotEditablePreview() {
    LBPasswordFieldProfiles(
        isEditable = false
    )
}


