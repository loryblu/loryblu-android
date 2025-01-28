package com.loryblu.feature.logbook.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.components.text_fields.TextFieldWithTitle
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.White
import com.loryblu.core.ui.utils.LBPreview
import com.loryblu.core.util.validators.InputValid
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.components.LBPasswordFieldProfiles
import com.loryblu.feature.logbook.ui.components.LBTextFieldProfiles
import com.loryblu.feature.logbook.ui.components.ProfilePictureComponent

@Composable
fun ParentProfileScreen() {
    ParentProfileContent()
//    ParentProfileContent(isEditable = false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ParentProfileContent(
    onBackButtonClicked: () -> Unit = {},
    isEditable: Boolean = true,
    onEditClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.appbar_title_parent_profile),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onBackButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ProfilePictureComponent(isEditable, onEditClicked)
                Spacer(modifier = Modifier.height(36.dp))
                if (!isEditable) {
                    BlockedActionsMessage(onAccessControlClick = {})
                }
                Spacer(modifier = Modifier.height(32.dp))
                UserInformation(isEditable)
                if (isEditable) {
                    Spacer(modifier = Modifier.weight(1f))
                    SaveAndCancelButton()
                }
            }
        }
    )
}

@Composable
private fun BlockedActionsMessage(onAccessControlClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_alert),
            contentDescription = "Aviso",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        val annotatedText = buildAnnotatedString {
            append("Ações bloqueadas! Você pode alterar as permissões em ")
            pushStringAnnotation(tag = "ACCESS_CONTROL", annotation = "access_control")
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Controle de Acesso.")
            }
            pop()
        }

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodyMedium,
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "ACCESS_CONTROL", start = offset, end = offset)
                    .firstOrNull()?.let {
                        onAccessControlClick()
                    }
            }
        )
    }
}

@Composable
private fun UserInformation(
    isEditable: Boolean,
) {

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TextFieldWithTitle(
            title = stringResource(R.string.name_of_the_parent)
        ) {
            LBTextFieldProfiles(
                onValueChange = {},
                placeholderRes = "Nome da pessoa responsável",
                value = "",
                icon = com.loryblu.core.ui.R.drawable.ic_user,
                error = InputValid.Valid,
                fieldFocus = {},
                isEditable = isEditable,
            )
        }

        EmailInformation(isEditable)
        LBPasswordFieldProfiles(isEditable)

    }
}

@Composable
private fun EmailInformation(isEditable: Boolean) {
    Column {
        TextFieldWithTitle(
            title = stringResource(com.loryblu.core.ui.R.string.email)
        ) {
            LBTextFieldProfiles(
                onValueChange = {},
                placeholderRes = "Email",
                value = "",
                icon = com.loryblu.core.ui.R.drawable.ic_email,
                error = InputValid.Valid,
                fieldFocus = {},
                isEditable = false,
                showTrailingIcon = !isEditable,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_alert),
                contentDescription = "Aviso",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            val annotatedText = buildAnnotatedString {
                append("Não é possível editar o e-mail. ")
                pushStringAnnotation(tag = "CONTACT", annotation = "contact")
                withStyle(
                    style = SpanStyle(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Entre em contato")
                }
                pop()
                append(" com o nosso suporte para mais informações.")
            }

            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.bodyMedium,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(tag = "CONTACT", start = offset, end = offset)
                        .firstOrNull()?.let { }
                }
            )
        }
    }
}


@Composable
private fun SaveAndCancelButton() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            border = BorderStroke(2.dp, LBSkyBlue),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel),
                color = LBSkyBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = LBSkyBlue
            ),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.save),
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@LBPreview
@Composable
private fun ParentProfileEditablePreview() {
    ParentProfileContent(isEditable = true)
}

@LBPreview
@Composable
private fun ParentProfileNotEditablePreview() {
    ParentProfileContent(isEditable = false)
}
